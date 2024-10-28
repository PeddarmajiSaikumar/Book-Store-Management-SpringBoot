package com.ust.book_service.converter;

import com.ust.book_service.dto.BookDto;
import com.ust.book_service.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    public BookDto toDto(Book book){
        return new BookDto(book.getId(),book.getTitle(), book.getAuthorId());
    }

    public Book toEntity(BookDto dto){
        Book book=new Book();
        book.setId(dto.id());
        book.setTitle(dto.title());
        book.setAuthorId(dto.authorId());
        return book;
    }
}
