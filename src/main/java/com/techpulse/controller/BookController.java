package com.techpulse.controller;

import com.techpulse.dto.BookDTO;
import com.techpulse.response.ApiResponse;
import com.techpulse.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> addBook(@Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO>> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookDTO));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }
}
