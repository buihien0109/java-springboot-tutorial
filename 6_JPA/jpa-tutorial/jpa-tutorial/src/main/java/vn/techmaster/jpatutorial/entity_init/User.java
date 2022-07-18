package vn.techmaster.jpatutorial.entity_init;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class User {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Email // Sử dụng validation để validate email
    private String email;

    @Column(name = "phone_number", columnDefinition = "varchar(10) NOT NULL")
    // Định nghĩa các thuộc tính của column giống trong SQL
    private String phoneNumber;

    @Length(min = 4, max = 8) // Sử dụng Validation để ràng buộc thuộc tính
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'https://via.placeholder.com/150'") // Có thể để null
    private String avatar;
}

/*
create table user_entity (
       id varchar(255) not null,
        avatar varchar(255) default 'https://via.placeholder.com/150' not null,
        email varchar(255),
        name varchar(255),
        password varchar(8),
        phone_number varchar(10) NOT NULL,
        primary key (id)
    )
*/
