package com.ust.author_service.converter;

import com.ust.author_service.dto.AuthorDto;
import com.ust.author_service.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter {

    public AuthorDto toDto(Author author){
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author toEntity(AuthorDto dto){
        Author author=new Author();
        author.setId(dto.id());
        author.setName(dto.name());
        return author;
    }
}
