package vn.techmaster.jpatutorial.id.id_auto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_auto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuto {

    // GenerationType.AUTO là kiểu generate primary key mặc định cho phép persistence provider tự lựa chọn kiểu mà nó muốn.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
