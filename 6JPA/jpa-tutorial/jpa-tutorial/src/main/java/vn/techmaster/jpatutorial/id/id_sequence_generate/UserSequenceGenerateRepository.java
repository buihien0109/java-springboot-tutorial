package vn.techmaster.jpatutorial.id.id_sequence_generate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSequenceGenerateRepository extends JpaRepository<UserSequenceGenerate, Integer> {
}