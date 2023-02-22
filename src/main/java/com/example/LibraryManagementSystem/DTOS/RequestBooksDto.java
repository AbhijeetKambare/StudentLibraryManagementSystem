package com.example.LibraryManagementSystem.DTOS;

import com.example.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RequestBooksDto {
    private int id;

    private String bookName;

    private int pages;

    private Genre genre;

    public RequestBooksDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
