package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}