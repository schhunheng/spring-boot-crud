package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.books.JdbcBookRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private JdbcBookRepository jdbcBookRepository;

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        try {
            jdbcBookRepository.save(new Book(book.getTitle(), book.getPages(), book.getAuthor()));
            return new ResponseEntity<>("Save Book successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {
            int result = jdbcBookRepository.deleteById(id);
            System.out.println(result);
            if (result == 0) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Delete Successful.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String name) {
        System.out.println(title + ":" + name);
        List<Book> books;
        if (title != null) {
            books = jdbcBookRepository.findByTitle(title);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        books = jdbcBookRepository.findBookAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
