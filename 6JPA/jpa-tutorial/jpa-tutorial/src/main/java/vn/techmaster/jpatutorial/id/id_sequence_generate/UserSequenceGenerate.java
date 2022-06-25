package vn.techmaster.jpatutorial.id.id_sequence_generate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_sequence_generate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSequenceGenerate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generate_seq")
    @SequenceGenerator(name = "user_sequence_generate_seq", initialValue = 10, allocationSize = 10)
    // initialValue : Giá trị bắt đầu
    // allocationSize : Mỗi lần tăng id lên bao nhiêu
    // https://stackoverflow.com/questions/2595124/how-does-the-jpa-sequencegenerator-annotation-work
    @Column(name = "id", nullable = false)
    private Integer id;
}