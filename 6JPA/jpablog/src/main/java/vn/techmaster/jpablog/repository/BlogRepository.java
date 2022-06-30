package vn.techmaster.jpablog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpablog.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> getByOrderByCreatedAtDesc();

    List<Blog> findByOrderByPulishedAtDesc(Pageable pageable);
}