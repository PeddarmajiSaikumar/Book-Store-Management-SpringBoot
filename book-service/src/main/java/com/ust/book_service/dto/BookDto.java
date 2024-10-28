package com.ust.book_service.dto;

public record BookDto(
   Long id,

   String title,

   Long authorId
) {
}
