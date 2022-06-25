package com.example.dtomapper.mapper;

import com.example.dtomapper.dto.AuthorDto;
import com.example.dtomapper.entity.Author;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorMapper {
    Author authorDtoToAuthor(AuthorDto authorDto);

    AuthorDto authorToAuthorDto(Author author);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Author updateAuthorFromAuthorDto(AuthorDto authorDto, @MappingTarget Author author);
}
