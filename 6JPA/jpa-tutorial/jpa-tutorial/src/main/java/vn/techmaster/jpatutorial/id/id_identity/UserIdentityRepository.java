package vn.techmaster.jpatutorial.id.id_identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, Integer> {
}