package com.example.demo.model;

public class Book {
    private long id;
    private String title;
    private String author;
    private int pages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(long id, String title, int pages, String author) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    public Book(String title, int pages, String author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", id=" + id + ", pages=" + pages + ", title=" + title + "]";
    }
}
