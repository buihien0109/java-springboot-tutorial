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

    public List<BlogDto> getAllBlogDto() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    public List<BlogDto> getBlogDtoByUserId(Integer id) {
        List<Blog> blogs = blogRepository.getAllByUser_Id(id);
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    public PaginationInfo<BlogInfo> getAllBlog(int page) {
        int limit = 6;
        int offset = (page - 1) * limit;

        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();
        List<BlogInfo> blogInfos = blogs.stream()
                .skip(offset)
                .limit(6)
                .collect(Collectors.toList());

        int totalItems = blogRepository.countBlogsByStatus(1);

        return new PaginationInfo<>(blogInfos, totalItems, page);
    }

    public List<BlogInfo> getBlogMostPopular(int limit) {
        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();
        return blogs.stream()
                .sorted((a, b) -> b.getCountComment() - a.getCountComment())
                .limit(limit).toList();
    }

    public BlogDetail getBlogDetailById(String id) {
        return blogRepository.getBlogDetailById(id);
    }

    public BlogDto getBlogById(String id) {
        Blog blog =  blogRepository.getBlogById(id);
        return modelMapper.map(blog, BlogDto.class);
    }

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
