package com.example.lab2.service;


import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, Category category, Long author, Integer availableCopies);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id, String name, Category category, Long author, Integer availableCopies);

    Optional<Book> update(Long id, BookDto bookDto);

    void delete(Long id);

    Optional<Book> markAsTaken(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

}
