package vn.techmaster.jpatutorial.id.id_composite_embedded_id;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompositeUsingEmbeddedIdRepository extends JpaRepository<UserCompositeUsingEmbeddedId, UserId> {
}