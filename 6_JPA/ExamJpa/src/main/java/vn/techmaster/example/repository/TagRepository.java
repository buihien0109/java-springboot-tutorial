package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}