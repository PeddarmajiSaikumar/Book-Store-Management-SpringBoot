package com.ust.book_service.controller;

import com.ust.book_service.converter.BookDtoConverter;
import com.ust.book_service.dto.AuthorDto;
import com.ust.book_service.dto.BookDto;
import com.ust.book_service.dto.CompleteDetails;
import com.ust.book_service.feign_clients.AuthorClient;
import com.ust.book_service.model.Book;
import com.ust.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDtoConverter bookDtoConverter;

    @Autowired
    private AuthorClient authorClient;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto dto){
        //create a new book
        Book book=bookDtoConverter.toEntity(dto);

        //save the book to the database
        book=bookService.createBook(book);
        var responseBody=bookDtoConverter.toDto(book);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks().stream()
                .map(book -> new BookDto(book.getId(), book.getTitle(), book.getAuthorId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){

        //get the book from the database

        Book book=bookService.getBookById(id);
        var responseBody=bookDtoConverter.toDto(book);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/bookdetails/{id}")
    public ResponseEntity<CompleteDetails> getCompleteDetails(@PathVariable Long id){
        Book book=bookService.getBookById(id);
        AuthorDto author= authorClient.getAuthorById(book.getAuthorId()).getBody();
        BookDto bookDto=bookDtoConverter.toDto(book);
        CompleteDetails responseBody=new CompleteDetails(bookDto,author);
        return ResponseEntity.ok(responseBody);
    }
}
