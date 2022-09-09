package com.example.demo.repository.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public class JdbcBookRepository implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO books (title,pages,author) VALUES(?,?,?)",
                book.getTitle(),
                book.getPages(),
                book.getAuthor());
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("DELETE from books where id=?", id);
    }

    @Override
    public List<Book> findBookAll() {
        System.out.println("Find All ");
        String query = "SELECT * from books";

        List<Book> books = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Book.class));
        return books;
    }

    @Override
    public List<Book> findByTitle(String title) {
        System.out.println("Find By title");
        String sql = "SELECT * from books WHERE title LIKE '%" + title + "%'";
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        return books;
    }

}
