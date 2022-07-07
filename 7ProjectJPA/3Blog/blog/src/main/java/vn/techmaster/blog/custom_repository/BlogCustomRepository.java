package vn.techmaster.blog.custom_repository;

import vn.techmaster.blog.dto.BlogDto;

import java.util.List;

public interface BlogCustomRepository {
    List<BlogDto> getListBlogDto();
}
