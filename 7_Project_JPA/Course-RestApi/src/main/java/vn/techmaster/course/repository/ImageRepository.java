package vn.techmaster.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.course.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}