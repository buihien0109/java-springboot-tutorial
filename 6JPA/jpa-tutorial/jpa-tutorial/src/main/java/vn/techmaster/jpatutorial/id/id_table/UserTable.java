package vn.techmaster.jpatutorial.id.id_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_sequence_table")
    @TableGenerator(name = "user_sequence_table", table = "user_sequence_table", initialValue = 10, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
}
