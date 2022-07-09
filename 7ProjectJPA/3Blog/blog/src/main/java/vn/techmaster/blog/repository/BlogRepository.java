package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.BlogDetail;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    @Query(name = "getAllBlogInfo", nativeQuery = true)
    List<BlogInfo> getAllBlogInfo();

    @Query(name = "getBlogDetailById", nativeQuery = true)
    BlogDetail getBlogDetailById(String id);

    int countBlogsByStatus(int status);

    Blog getBlogById(String id);

    List<Blog> getAllByUser_Id(Integer id);
}