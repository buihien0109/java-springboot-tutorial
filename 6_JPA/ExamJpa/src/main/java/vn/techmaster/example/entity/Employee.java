package vn.techmaster.example.entity;

import lombok.*;

import javax.persistence.*;

// c1 : Lây thông tin entity bằng @NamedQuery
@NamedQuery(
        name = "getUserInfoByIdNamedQuery",
        query = "select e from Employee e where e.id = ?1"
)
// c2 : Lây thông tin entity bằng @NamedNativeQuery binding trực tiếp vào class
@NamedNativeQuery(
        name = "getUserInfoByIdNativeQuery",
        query = "select * from employee e where e.id = ?1",
        resultClass = Employee.class
)
// c3 : Lây thông tin entity bằng @NamedNativeQuery binding qua @SqlResultSetMapping
@SqlResultSetMapping(
        name = "employeeInfo",
        entities = {
                @EntityResult(
                        entityClass = Employee.class
                )
        }
)
@NamedNativeQuery(
        name = "getUserInfoByIdNativeQueryOther",
        query = "select * from employee e where e.id = ?1",
        resultSetMapping = "employeeInfo"
)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
