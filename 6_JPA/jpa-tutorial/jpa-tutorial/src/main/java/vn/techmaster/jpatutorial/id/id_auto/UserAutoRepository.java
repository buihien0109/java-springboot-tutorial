package vn.techmaster.jpatutorial.id.id_auto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAutoRepository extends JpaRepository<UserAuto, Long> {
}