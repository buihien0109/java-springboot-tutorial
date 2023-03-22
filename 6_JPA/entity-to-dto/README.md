## Query DTO trong JPA

Ví dụ chúng có entity như sau

```java

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
```

Bây giờ chúng ta muốn trả về dữ liệu bao gồm 1 số thông tin : **id**, **name**, **email**

Tạo class **UserDto** để trả về kết quả

```java

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
}
```

Tạo repository tương ứng với entity User (Nên sử dụng JPA Buddy để làm)

```java

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}
```

### 1. Sử dụng class Mapper

Có nhiều cách để áp dụng class Mapper :

- Custom class Mapper
- Sử dụng MapStruct
- Sử dụng ModelMapper

Trong hướng dẫn này, chúng ta sẽ sử dụng Mapstruct

Add dependence của mapstruct vào file POM

```xml

<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.1.Final</version>
    <scope>compile</scope>
</dependency>

<dependency>
<groupId>org.projectlombok</groupId>
<artifactId>lombok-mapstruct-binding</artifactId>
<version>0.2.0</version>
</dependency>
```

và

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <source>17</source> <!-- depending on your project -->
        <target>17</target> <!-- depending on your project -->
        <annotationProcessorPaths>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.5.1.Final</version>
            </path>
            <!-- other annotation processors -->
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.24</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

Sau khi add dependence và compiler tương ứng, chúng ta có thể tạo ra model DTO (nên sử dụng JPA Buddy để tạo)

Sau khi tạo xong model DTO chúng ta tiến hành chạy `mvn clean:install` để tiến hành generate code (hoặc có thể thao tác
trên tab maven của intellij)

Việc tạo UserDto từ User, chúng ta cần query ở dạng User sau đó convert qua UserDto

```java

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperImpl userMapper;

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }
}
```

### 2. Sử dụng @NamedNativeQuery và @NamedQuery trong Entity

Bổ sung **@NamedNativeQuery** để query dữ liệu ở dạng native query và nên nhớ đặt tên cho phương thức đó để gọi trong **
UserInterface**, và dữ liệu query được chúng ta sẽ đặt tên để tiếp tục binding vào class **UserDto** (mặc định kết quả
sẽ binding vào class Entity)

Sử dụng kết quả từ **@NamedNativeQuery** để binding dữ liệu vào class mong muốn sử dụng **@SqlResultSetMapping**, giá
trị của **name** phải trùng với **resultSetMapping** trong **@NamedNativeQuery**, và thứ tự columns phải được binding
theo đúng thứ tự

```java
@NamedNativeQuery(
        name = "getUserInfo",
        resultSetMapping = "userInfo",
        query = "SELECT u.id, u.name, u.email " +
                "FROM user u"
)
@SqlResultSetMapping(
        name = "userInfo",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email")
                }
        )
)
```

Trong **UserInterface** chúng ta định nghĩa phương thức **getAllUserDtoInfoOther()** trả về List<UserDto>. Cần sử dụng
@Query cho phương thức này với giá **nativeQuery = true** và **name = "getUserInfo"** (Lưu ý **name** trùng với **name**
trong **@NamedNativeQuery**)

```java

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    @Query(nativeQuery = true, name = "getUserInfo")
    List<UserDto> getAllUserDtoInfoOther();
}
```

### 3. Sử dụng JPQL (JPA Query Language)

Với cách này thì đơn giản là chúng ta sẽ JPA Query Language để query dữ liệu thay vì sử dụng native query khá dài dòng
như ở trên

```java

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    @Query("select new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) from User u")
    List<UserDto> getAllUserDtoInfo();
}
```

### 4. Sử dụng Custom Interface

Định nghĩa interface **UserRepositoryCustom**, đây là custom interface với các chức năng mở rộng và dễ dàng triển khai

```java
public interface UserRepositoryCustom {
    List<UserDto> getAllUserDtoByCreateQuery();

    List<UserDto> getAllUserDtoByCreateNamedQuery();
}
```

Trong interface **UserRepository** ngoài extends interface **JpaRepository** thì chúng ta extends bổ sung thêm **UserRepositoryCustom**

```java
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}
```

#### 4.1 Sử dụng JPQL Query

```java
@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDto> getAllUserDtoByCreateQuery() {
        String query = "SELECT new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) FROM User u";
        TypedQuery<UserDto> typedQuery = entityManager.createQuery(query, UserDto.class);
        return typedQuery.getResultList();
    }
}
```

#### 4.2 Sử dụng NamedQuery

Với cách này chúng ta cần định nghĩa **NamedQuery** bên phía Entity

```java
@NamedQuery(
        name = "getUserInfoOther",
        query = "SELECT new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) " +
        "FROM User u"
)
```

và thực hiện gọi **NamedQuery** đã định nghĩa trong phương thức **createNamedQuery**

```java
@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;
    
    public List<UserDto> getAllUserDtoByCreateNamedQuery() {
        TypedQuery<UserDto> typedQuery = entityManager.createNamedQuery("getUserInfoOther", UserDto.class);
        return typedQuery.getResultList();
    }
}
```



