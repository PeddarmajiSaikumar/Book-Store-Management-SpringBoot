package com.ust.book_service.feign_clients;

import com.ust.book_service.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-service",url = "http://localhost:8080")
public interface AuthorClient {
    @GetMapping("/api/v1/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id);
}
