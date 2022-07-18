package vn.techmaster.jpatutorial.id.id_composite_id_class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCompositeUsingIdClassRepository extends JpaRepository<UserCompositeUsingIdClass, UserId> {
}