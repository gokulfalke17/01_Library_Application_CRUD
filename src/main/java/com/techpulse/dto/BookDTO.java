package com.techpulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @NotBlank(message = "Publisher must not be blank")
    private String publisher;

    @NotBlank(message = "ISBN must not be blank")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    private String isbn;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @NotBlank(message = "Language must not be blank")
    private String language;

    @NotNull(message = "Total pages is required")
    @Min(value = 1, message = "Total pages must be at least 1")
    private Integer totalPages;

    @NotBlank(message = "Edition must not be blank")
    private String edition;

    @NotNull(message = "Publisher year is required")
    @Min(value = 1500)
    @Max(value = 2100)
    private Integer publisherYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull
    @Min(0)
    private Integer availableCopies;

    @NotNull
    @Min(0)
    private Integer totalCopies;

    @NotNull(message = "Availability must be specified")
    private Boolean isAvailable;

    @Size(max = 1000, message = "Description can be max 1000 characters")
    private String description;
}
