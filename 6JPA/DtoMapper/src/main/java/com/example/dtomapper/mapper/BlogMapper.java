package com.example.dtomapper.mapper;

import com.example.dtomapper.dto.BlogDto;
import com.example.dtomapper.entity.Blog;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BlogMapper {
    Blog blogDtoToBlog(BlogDto blogDto);

    BlogDto blogToBlogDto(Blog blog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Blog updateBlogFromBlogDto(BlogDto blogDto, @MappingTarget Blog blog);
}
