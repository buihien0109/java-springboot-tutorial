package vn.techmaster.jpatutorial.id.id_composite_id_class;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_composite_using_id_class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserId.class)
public class UserCompositeUsingIdClass {
    // Tự set id
    @Id
    private int id;

    @Id
    private String name;

    // Generate id tự động
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_composite_id_seq")
//    @SequenceGenerator(name = "user_composite_id_seq", sequenceName = "user_composite_id_seq")
//    @Column(nullable = false)
//    private int id;
//
//    @Id
//    @GenericGenerator(name = "custom_gen", strategy = "vn.techmaster.jpatutorial.id.id_custom_generate.CustomIdGenerator")
//    @GeneratedValue(generator = "custom_gen")
//    private String name;
}