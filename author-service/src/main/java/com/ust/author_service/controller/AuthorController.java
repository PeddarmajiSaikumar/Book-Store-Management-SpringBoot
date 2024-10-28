package com.ust.author_service.controller;

import com.ust.author_service.converter.AuthorDtoConverter;
import com.ust.author_service.dto.AuthorDto;
import com.ust.author_service.model.Author;
import com.ust.author_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorDtoConverter authorDtoConverter;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto dto){

        //create a new author
        Author author=authorDtoConverter.toEntity(dto);

        //save the author to the database
        author=authorService.createAuthor(author);
        var responseBody=authorDtoConverter.toDto(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

    }

    @GetMapping
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAllAuthors().stream()
                .map(author -> new AuthorDto(author.getId(),author.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id){

        //get the author from the database
        Author author=authorService.getAuthorById(id);
        var responseBody=authorDtoConverter.toDto(author);
        return ResponseEntity.ok(responseBody);

    }

}
