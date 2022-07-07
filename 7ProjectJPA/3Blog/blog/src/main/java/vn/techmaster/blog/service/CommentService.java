package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentInfo> getCommentsByBlogId(String blogId) {
        return commentRepository.getCommentsByBlogId(blogId);
    }
}
