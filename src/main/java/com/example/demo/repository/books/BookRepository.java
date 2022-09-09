package com.example.demo.repository.books;

import java.util.List;

import com.example.demo.model.Book;

public interface BookRepository {
    int save(Book book);

    int deleteById(long id);

    List<Book> findBookAll();

    List<Book> findByTitle(String title);
}
