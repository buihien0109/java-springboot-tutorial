package vn.techmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}