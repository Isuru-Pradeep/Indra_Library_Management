package com.example.indralibrarymanagement;

public class Book {
    public long book_id;
    public String bookName;
    public String publisherName;
    public String authorName;


    public Book(long book_id, String bookName, String publisherName, String authorName) {
        this.book_id = book_id;
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.authorName = authorName;
    }
}
