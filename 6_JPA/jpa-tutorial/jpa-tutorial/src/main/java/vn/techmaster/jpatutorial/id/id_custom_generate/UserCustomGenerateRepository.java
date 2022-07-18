package vn.techmaster.jpatutorial.id.id_custom_generate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomGenerateRepository extends JpaRepository<UserCustomGenerate, String> {
}