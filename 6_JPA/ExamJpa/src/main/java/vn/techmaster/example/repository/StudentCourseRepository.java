package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.StudentCourse;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    @Query("select avg(sc.score) from StudentCourse sc where sc.course.id = ?1 group by sc.course.id")
    Double calculateAvgScore(Long courseId);
}