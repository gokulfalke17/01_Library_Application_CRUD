package com.techpulse.service;

import com.techpulse.dto.BookDTO;
import com.techpulse.response.ApiResponse;

import java.util.List;

public interface IBookService {

    ApiResponse<BookDTO> addBook(BookDTO bookDTO);
    ApiResponse<List<BookDTO>> getAllBooks();
    ApiResponse<BookDTO> getBookById(Long bookId);
    ApiResponse<BookDTO> updateBook(Long bookId, BookDTO bookDTO);
    ApiResponse<Void> deleteBook(Long bookId);
}
