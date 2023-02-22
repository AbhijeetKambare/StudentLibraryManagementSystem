package com.example.LibraryManagementSystem.DTOS;

import java.util.List;

public class RequestAuthorDto {
    private int id;

    private String authorName;

    private int age;

    private String country;

    private double rating;
    private List<RequestBooksDto> booksDtoList;

    public RequestAuthorDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<RequestBooksDto> getBooksDtoList() {
        return booksDtoList;
    }

    public void setBooksDtoList(List<RequestBooksDto> booksDtoList) {
        this.booksDtoList = booksDtoList;
    }
}
