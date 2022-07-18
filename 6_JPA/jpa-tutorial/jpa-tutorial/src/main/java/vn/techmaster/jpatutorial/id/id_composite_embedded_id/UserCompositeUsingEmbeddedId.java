package vn.techmaster.jpatutorial.id.id_composite_embedded_id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_composite_using_embedded_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCompositeUsingEmbeddedId {
    @EmbeddedId
    private UserId userId;
}