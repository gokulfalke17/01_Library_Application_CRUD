package com.techpulse.service.impl;

import com.techpulse.dto.BookDTO;
import com.techpulse.entity.Book;
import com.techpulse.exception.ResourceNotFoundException;
import com.techpulse.mapper.BookMapper;
import com.techpulse.repository.IBookRepository;
import com.techpulse.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    void setUp() {

        bookDTO = BookDTO.builder()
                .title("Java Programming")
                .author("James Gosling")
                .publisher("Oracle")
                .isbn("1234567890123")
                .category("Programming")
                .language("English")
                .totalPages(500)
                .edition("1st")
                .publisherYear(2023)
                .price(499.0)
                .availableCopies(10)
                .totalCopies(20)
                .isAvailable(true)
                .description("Core Java Book")
                .build();

        book = BookMapper.toEntity(bookDTO);
    }

    // ===================== addBook =====================

    @Test
    void addBook_success() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        ApiResponse<BookDTO> response = bookService.addBook(bookDTO);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Book Added Successfully", response.getMessage());
        assertNotNull(response.getData());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    // ===================== getAllBooks =====================

    @Test
    void getAllBooks_success() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        ApiResponse<List<BookDTO>> response = bookService.getAllBooks();

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getAllBooks_noBooksFound_shouldThrowException() {
        when(bookRepository.findAll()).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.getAllBooks());

        verify(bookRepository, times(1)).findAll();
    }

    // ===================== getBookById =====================

    @Test
    void getBookById_success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        ApiResponse<BookDTO> response = bookService.getBookById(1L);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Book Found", response.getMessage());
        assertNotNull(response.getData());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_notFound_shouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.getBookById(1L));

        verify(bookRepository, times(1)).findById(1L);
    }

    // ===================== updateBook =====================

    @Test
    void updateBook_success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        ApiResponse<BookDTO> response = bookService.updateBook(1L, bookDTO);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Book Updated Successfully", response.getMessage());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void updateBook_notFound_shouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.updateBook(1L, bookDTO));

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }

    // ===================== deleteBook =====================

    @Test
    void deleteBook_success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        ApiResponse<Void> response = bookService.deleteBook(1L);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Book Deleted Successfully", response.getMessage());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(any(Book.class));
    }

    @Test
    void deleteBook_notFound_shouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.deleteBook(1L));

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).delete(any(Book.class));
    }
}
