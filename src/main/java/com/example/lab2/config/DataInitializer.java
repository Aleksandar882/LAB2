package com.example.lab2.config;

import com.example.lab2.model.Category;
import com.example.lab2.service.AuthorService;
import com.example.lab2.service.BookService;
import com.example.lab2.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    private final CountryService countryService;

    private final BookService bookService;

    public DataInitializer(AuthorService authorService, CountryService countryService, BookService bookService) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.bookService = bookService;
    }


    @PostConstruct
    public void initData() {
        this.countryService.create("USA", "North America" );
        this.countryService.create("Great Britain", "Europe" );
        this.countryService.create("Russia", "Europe" );
        this.countryService.create("Italy", "Europe" );
        this.authorService.create("Clive","Lewis", 2L);
        this.authorService.create("William","Shakespeare", 2L);
        this.authorService.create("Dante","Alighieri", 4L);
        this.authorService.create("Fyodor","Dostoevsky", 3L);
        this.authorService.create("Dan","Brown", 1L);
        this.authorService.create("Charles","Dickens", 2L);
        this.authorService.create("Ron","Chernow", 1L);
        this.bookService.create("The Lion, The Witch and the Wardrobe", Category.FANTASY,5L,15);
        this.bookService.create("The Divine Comedy", Category.CLASSICS,7L,5);
        this.bookService.create("The Brothers Karamazov", Category.NOVEL,8L,10);
        this.bookService.create("The Da Vinci Code", Category.THRILLER,9L,5);
        this.bookService.create("A Tale of Two Cities", Category.HISTORY,10L,20);
        this.bookService.create("Alexander Hamilton", Category.BIOGRAPHY,11L,7);
        this.bookService.create("Romeo And Juliet", Category.DRAMA,6L,9);
    }
}