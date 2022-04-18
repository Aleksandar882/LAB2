package com.example.lab2.service;

import com.example.lab2.model.Author;
import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.model.dto.BookDto;
import com.example.lab2.model.exceptions.AuthorNotFoundException;
import com.example.lab2.model.exceptions.BookNotFoundException;
import com.example.lab2.model.exceptions.InvalidAuthorIdException;
import com.example.lab2.model.exceptions.InvalidBookIdException;
import com.example.lab2.repository.AuthorRepository;
import com.example.lab2.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author= this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        return Optional.of(this.bookRepository.save (new Book(name,category,author,availableCopies)));
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author= this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author= this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        Book book= this.findById(id).orElseThrow(()->new BookNotFoundException(id));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Author author= this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        Book book= this.findById(id).orElseThrow(()->new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book= this.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int copies= book.getAvailableCopies();
        copies--;
        if(copies<0){
            copies=0;
        }
        book.setAvailableCopies(copies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }
}
