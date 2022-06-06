package com.example.blogbackend.service.admin;

import com.example.blogbackend.model.Blog;
import com.example.blogbackend.model.User;
import com.example.blogbackend.model.mapper.BlogMapper;
import com.example.blogbackend.model.request.CreateBlogRequest;
import com.example.blogbackend.model.request.UpdateBlogRequest;
import com.example.blogbackend.model.response.BlogResponse;
import com.example.blogbackend.model.response.BlogReturn;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BlogAdminService {
    private final int BLOG_OF_PAGE = 10;
    private List<Blog> blogs;

    private final Random rd;
    private final Slugify slugify;
    private final UserAdminService userAdminService;
    private final BlogMapper blogMapper;

    public BlogAdminService(UserAdminService userAdminService, Slugify slugify, Random rd, BlogMapper blogMapper) {
        this.userAdminService = userAdminService;
        this.slugify = slugify;
        this.rd = rd;
        this.blogMapper = blogMapper;

        init();
    }

    // Tạo 1 số bài blog
    public void init() {
        // Fake content
        Faker faker = new Faker();

        // Lấy danh sách user
        List<User> users = userAdminService.getUsers();

        // Tạo danh sách bài viết
        blogs = new ArrayList<>();
        IntStream.range(1, 30).forEach(n -> {
            Blog blog = new Blog();

            // Set nội dung bài viết
            blog.setId(n);
            String title = faker.lorem().sentence(10);
            blog.setTitle(title);
            blog.setContent(faker.lorem().sentence(200));
            blog.setDescription(faker.lorem().sentence(30));
            blog.setSlug(slugify.slugify(title));
            blog.setImage("blog-image-0" + (rd.nextInt(10) + 1) + ".jpeg");

            // Set status
            boolean status = rd.nextInt(100) > 50;
            blog.setStatus(status);

            // Set createdAt
            LocalDate createdAt = LocalDate.now().minusDays(rd.nextInt(20));
            blog.setCreatedAt(createdAt);

            // Set publishedAt
            if (status) {
                LocalDate publishedAt = createdAt.plusDays(1);
                blog.setPublishedAt(publishedAt);
            }

            // Set AuthorId
            User user = users.get(rd.nextInt(rd.nextInt(users.size()) + 1));
            blog.setAuthorId(user.getId());

            blogs.add(blog);
        });
    }

    // Lấy danh sách blog được sắp xếp theo ngày tạo giảm dần
    public List<Blog> getBlogs() {
        return blogs
                .stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách blog được phân trang
    public BlogReturn getBlogs(int page) {
        // Danh sách Blog
        List<BlogResponse> blogsReturn = blogs
                .stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .skip((long) (page - 1) * BLOG_OF_PAGE)
                .limit(BLOG_OF_PAGE)
                .map(blogMapper::toBlogResponse)
                .toList();

        // Tính tổng số page
        int totalPage = (int) Math.ceil((double) blogs.size() / BLOG_OF_PAGE);

        return new BlogReturn(blogsReturn, totalPage);
    }

    // Lấy chi tiết bài viết
    public Blog getBlogById(int authorId, int blogId) {
        // Kiểm tra user có tồn tại hay không
        if (userAdminService.findUser(authorId).isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + authorId);
        }

        // Kiểm tra blog có tồn tại hay không
        Optional<Blog> blogOptional = findBlog(blogId);
        if (blogOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy blog có id = " + blogId);
        }

        return blogOptional.get();
    }

    // Tạo blog mới
    public Blog createBlog(int authorId, CreateBlogRequest request) {
        // Kiểm tra user có tồn tại hay không
        if (userAdminService.findUser(authorId).isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + authorId);
        }

        // Khởi tạo đối tượng blog mới
        Blog blog = new Blog();

        // Chèn các thông tin cho blog
        blog.setId(rd.nextInt(1000));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setCreatedAt(LocalDate.now());
        blog.setStatus(request.isStatus());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setAuthorId(authorId);

        // Thêm vào List để quản lý
        blogs.add(blog);

        // Trả về blog sau khi tạo
        return blog;
    }

    // Cập nhật thông tin của blog
    public Blog updateBlog(int authorId, int blogId, UpdateBlogRequest request) {
        // Kiểm tra user có tồn tại hay không
        if (userAdminService.findUser(authorId).isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + authorId);
        }

        // Kiểm tra blog có tồn tại hay không
        Optional<Blog> blogOptional = findBlog(blogId);
        if (blogOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy blog có id = " + blogId);
        }

        // Lấy ra thông tin của blog trong blogOptional
        Blog blog = blogOptional.get();

        // Set lại các thông tin cho blog
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setStatus(request.isStatus());
        blog.setSlug(slugify.slugify(request.getTitle()));

        // Trả về blog
        return blog;
    }

    // Xóa blog
    public void deleteBlog(int authorId, int blogId) {
        // Kiểm tra user có tồn tại hay không
        if (userAdminService.findUser(authorId).isEmpty()) {
            throw new RuntimeException("Không tìm thấy user có id = " + authorId);
        }

        // Kiểm tra blog có tồn tại hay không
        Optional<Blog> blogOptional = findBlog(blogId);
        if (blogOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy blog có id = " + blogId);
        }

        // Xóa blog theo id
        blogs.removeIf(b -> b.getId() == blogId);
    }

    // Helper method : Tìm kiếm blog theo id
    public Optional<Blog> findBlog(int id) {
        return blogs.stream().filter(b -> b.getId() == id).findFirst();
    }
}
