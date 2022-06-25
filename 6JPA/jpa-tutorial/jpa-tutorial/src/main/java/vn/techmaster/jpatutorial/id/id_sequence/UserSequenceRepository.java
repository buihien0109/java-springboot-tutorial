package vn.techmaster.jpatutorial.id.id_sequence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSequenceRepository extends JpaRepository<UserSequence, Integer> {
}