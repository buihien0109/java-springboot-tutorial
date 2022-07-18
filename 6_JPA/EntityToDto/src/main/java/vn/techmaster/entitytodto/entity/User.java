package vn.techmaster.entitytodto.entity;

import lombok.*;
import vn.techmaster.entitytodto.dto.UserDto;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@NamedQuery(
        name = "getUserInfoOther",
        query = "SELECT new vn.techmaster.entitytodto.dto.UserDto(u.id, u.name, u.email) " +
        "FROM User u"
)
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