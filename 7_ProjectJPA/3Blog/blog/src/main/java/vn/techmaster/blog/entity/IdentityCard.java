package vn.techmaster.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identity_card")
public class IdentityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expried", columnDefinition = "TIMESTAMP")
    private LocalDateTime expried;

    @Column(name = "issued", columnDefinition = "TIMESTAMP")
    private LocalDateTime issued;

    @PrePersist
    public void prePersist() {
        issued = LocalDateTime.now().minusYears(2);
        expried = LocalDateTime.now().plusYears(2);
    }
}