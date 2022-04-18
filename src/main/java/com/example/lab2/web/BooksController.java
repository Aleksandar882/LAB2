package com.example.lab2.web;

import com.example.lab2.model.Author;
import com.example.lab2.model.Book;
import com.example.lab2.model.Category;
import com.example.lab2.service.AuthorService;
import com.example.lab2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class BooksController {

   private final AuthorService authorService;
   private final BookService bookService;

    public BooksController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping({"/","/books"})
    public String showBooks(Model model) {
        List<Book> books=this.bookService.listAll();
        List<Author> authors = this.authorService.listAll();

        model.addAttribute("books",books);
        model.addAttribute("authors",authors);
        return "list.html";
    }

    @GetMapping("/books/add")
    public String showAdd(Model model) {
        List<Author> authors = this.authorService.listAll();
        List<Category> categories= Arrays.asList(Category.values());
        model.addAttribute("authors",authors);
        model.addAttribute("categories",categories);
        return "form.html";
    }

    @GetMapping("/books/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        if(this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.listAll();
            List<Category> categories = Arrays.asList(Category.values());
            model.addAttribute("book", book);
            model.addAttribute("authors", authors);
            model.addAttribute("categories", categories);
            return "form.html";
        }
        return "redirect:/books?error=ProductNotFound";
    }

    @PostMapping("/books")
    public String create(@RequestParam String name,
                         @RequestParam Category category,
                         @RequestParam Long authorId,
                         @RequestParam Integer availableCopies) {
        this.bookService.create(name, category, authorId, availableCopies);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Category category,
                         @RequestParam Long authorId,
                         @RequestParam Integer availableCopies) {
        this.bookService.update(id,name, category, authorId, availableCopies);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/mark_as_taken")
    public String markAsTaken(@PathVariable Long id) {
        this.bookService.markAsTaken(id);
        return "redirect:/books";
    }



}
