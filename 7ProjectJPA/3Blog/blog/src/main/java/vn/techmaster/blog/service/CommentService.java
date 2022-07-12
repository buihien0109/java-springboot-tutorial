package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentInfo> getCommentsByBlogId(String blogId) {
        return commentRepository.getCommentsByBlogId(blogId);
    }

    public List<CommentInfo> getComments(int limit) {
        return commentRepository.getComments()
                .stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}
