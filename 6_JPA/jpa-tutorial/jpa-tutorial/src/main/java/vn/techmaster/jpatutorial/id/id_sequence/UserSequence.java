package vn.techmaster.jpatutorial.id.id_sequence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_sequence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
}