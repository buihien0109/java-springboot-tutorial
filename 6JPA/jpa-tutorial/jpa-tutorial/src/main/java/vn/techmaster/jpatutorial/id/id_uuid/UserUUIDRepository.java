package vn.techmaster.jpatutorial.id.id_uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserUUIDRepository extends JpaRepository<UserUUID, UUID> {
}