package vn.techmaster.jpatutorial.id.id_custom_generate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_custom_generate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCustomGenerate {
    @Id
    @GenericGenerator(name = "custom_gen", strategy = "vn.techmaster.jpatutorial.id.id_custom_generate.CustomIdGenerator")
    @GeneratedValue(generator = "custom_gen")
    @Column(name = "id", nullable = false)
    private String id;
}