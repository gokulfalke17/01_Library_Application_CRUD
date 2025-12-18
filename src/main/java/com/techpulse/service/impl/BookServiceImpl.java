package com.techpulse.service.impl;

import com.techpulse.dto.BookDTO;
import com.techpulse.entity.Book;
import com.techpulse.exception.ResourceNotFoundException;
import com.techpulse.mapper.BookMapper;
import com.techpulse.repository.IBookRepository;
import com.techpulse.response.ApiResponse;
import com.techpulse.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public ApiResponse<BookDTO> addBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);

        return ApiResponse.<BookDTO>builder()
                .success(true)
                .message("Book Added Successfully")
                .status(HttpStatus.CREATED)
                .data(BookMapper.toDTO(savedBook))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<BookDTO>> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No Books Available");
        }

        List<BookDTO> dtoList = books.stream()
                .map(BookMapper::toDTO)
                .toList();

        return ApiResponse.<List<BookDTO>>builder()
                .success(true)
                .message("Books Found")
                .status(HttpStatus.OK)
                .data(dtoList)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<BookDTO> getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Not Found With Id :: " + bookId));

        return ApiResponse.<BookDTO>builder()
                .success(true)
                .message("Book Found")
                .status(HttpStatus.OK)
                .data(BookMapper.toDTO(book))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<BookDTO> updateBook(Long bookId, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Not Found With Id :: " + bookId));

        Book updatedBook = BookMapper.updateEntityFromDTO(bookDTO, existingBook);
        Book savedBook = bookRepository.save(updatedBook);

        return ApiResponse.<BookDTO>builder()
                .success(true)
                .message("Book Updated Successfully")
                .status(HttpStatus.OK)
                .data(BookMapper.toDTO(savedBook))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Not Found With Id :: " + bookId));

        bookRepository.delete(book);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Book Deleted Successfully")
                .status(HttpStatus.OK)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
