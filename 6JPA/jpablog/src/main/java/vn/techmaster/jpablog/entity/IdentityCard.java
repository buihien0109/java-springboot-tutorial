package vn.techmaster.jpablog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @PostPersist
    public void postPersist() {
        expried = LocalDateTime.now().plusYears(2);
        issued = LocalDateTime.now().minusYears(2);
    }
}