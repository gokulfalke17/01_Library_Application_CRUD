package com.techpulse.repository;

import com.techpulse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {

}
