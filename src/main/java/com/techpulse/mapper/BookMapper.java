package com.techpulse.mapper;

import com.techpulse.dto.BookDTO;
import com.techpulse.entity.Book;

import java.time.LocalDateTime;

public class BookMapper {

    public static Book updateEntityFromDTO(BookDTO dto, Book existingBook) {

        existingBook = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .publisher(dto.getPublisher())
                .language(dto.getLanguage())
                .category(dto.getCategory())
                .totalPages(dto.getTotalPages())
                .edition(dto.getEdition())
                .publisherYear(dto.getPublisherYear())
                .price(dto.getPrice())
                .availableCopies(dto.getAvailableCopies())
                .description(dto.getDescription())
                .isAvailable(dto.getAvailableCopies() > 0)

                .bookId(existingBook.getBookId())
                .totalCopies(existingBook.getTotalCopies())

                .build();

        return existingBook;
    }


    //Entity <--> DTO
    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .language(book.getLanguage())
                .totalPages(book.getTotalPages())
                .edition(book.getEdition())
                .publisherYear(book.getPublisherYear())
                .price(book.getPrice())
                .availableCopies(book.getAvailableCopies())
                .totalCopies(book.getTotalCopies())
                .isAvailable(book.getIsAvailable())
                .description(book.getDescription())
                .build();
    }

    //DTO <--> Entity
    public static Book toEntity(BookDTO dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .isbn(dto.getIsbn())
                .category(dto.getCategory())
                .language(dto.getLanguage())
                .totalPages(dto.getTotalPages())
                .edition(dto.getEdition())
                .publisherYear(dto.getPublisherYear())
                .price(dto.getPrice())
                .availableCopies(dto.getAvailableCopies())
                .totalCopies(dto.getTotalCopies())
                .isAvailable(dto.getIsAvailable())
                .description(dto.getDescription())
                .build();
    }
}
