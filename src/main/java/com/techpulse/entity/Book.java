package com.techpulse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String category;
    private String language;

    private Integer totalPages;
    private String edition;
    private Integer publisherYear;

    private Double price;
    private Integer availableCopies;
    private Integer totalCopies;

    private Boolean isAvailable;

    private LocalDate publishedDate;
    private LocalDate addedDate;
    private LocalDate updatedDate;

    private String description;


}
