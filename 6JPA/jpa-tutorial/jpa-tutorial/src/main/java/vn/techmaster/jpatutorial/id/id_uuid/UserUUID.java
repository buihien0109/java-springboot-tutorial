package vn.techmaster.jpatutorial.id.id_uuid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_uuid")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUUID {
//    @Id
//    @Column(name = "id", nullable = false, length = 16)
//    @GeneratedValue
//    private UUID id;
//    private UUID id = UUID.randomUUID();

    // https://thorben-janssen.com/generate-uuids-primary-keys-hibernate/
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
}