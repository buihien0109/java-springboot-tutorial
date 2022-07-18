package vn.techmaster.blog.service;

import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogDetail;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.dto.PaginationInfo;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.entity.User;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.repository.UserRepository;
import vn.techmaster.blog.request.CreateBlogRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private Slugify slugify;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Lấy danh sách tất cả các bài blog ở dạng DTO
    public List<BlogDto> getAllBlogDto() {
        // Lấy danh sách tất cả các bài blog
        List<Blog> blogs = blogRepository.findAll();

        // Chuyển từ dạng Blog -> BlogDTO
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả các bài blog ở dạng DTO của user
    public List<BlogDto> getBlogDtoByUserId(Integer id) {
        // Lấy danh sách tất cả các bài blog của user
        List<Blog> blogs = blogRepository.getAllByUser_Id(id);

        // Chuyển từ dạng Blog -> BlogDTO
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả các bài blog ở dạng BlogInfo (Có phân trang)
    public PaginationInfo<BlogInfo> getAllBlog(int page) {
        // Giới hạn 6 bài blog 1 trang
        int limit = 6;

        // Tính toán offset để lấy ra bài blog phù hợp
        int offset = (page - 1) * limit;

        // Lấy danh sách tất cả các bài blog
        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();

        // Lấy danh sách blog theo từng trang
        List<BlogInfo> blogInfos = blogs.stream()
                .skip(offset)
                .limit(6)
                .collect(Collectors.toList());

        // Tổng số bài blog theo trạng thái
        int totalItems = blogRepository.countBlogsByStatus(1);

        return new PaginationInfo<>(blogInfos, totalItems, page);
    }

    // Lấy danh sách các bài blog phổ biến (có lượng comment giảm dần) ở dạng BlogInfo
    public List<BlogInfo> getBlogMostPopular(int limit) {
        // Lấy tất cả các bài blog ở dạng BlogInfo
        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();

        // Sắp xếp lại theo thứ tự comment giảm dần
        return blogs.stream()
                .sorted((a, b) -> b.getCountComment() - a.getCountComment())
                .limit(limit).toList();
    }

    // Lấy thông tin chi tiết bài blog ở dạng BlogDetail
    public BlogDetail getBlogDetailById(String id) {
        return blogRepository.getBlogDetailById(id);
    }

    // Lấy thông tin chi tiết bài blog ở dạng BlogDto
    public BlogDto getBlogById(String id) {
        Blog blog =  blogRepository.getBlogById(id);
        return modelMapper.map(blog, BlogDto.class);
    }

    // Tạo bài blog mới
    public Blog createBlog(int userId, CreateBlogRequest request) {
        // Lấy thông tin của user
        User user = userRepository.getUserById(userId, User.class);

        // Lấy danh sách category
        List<Category> categories = categoryRepository.getByIdIn(request.getCategories());

        // Tạo blog với các thông tin được gửi lên
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .content(request.getContent())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .categories(categories)
                .user(user)
                .build();

        blogRepository.save(blog);

        return blog;
    }
}
