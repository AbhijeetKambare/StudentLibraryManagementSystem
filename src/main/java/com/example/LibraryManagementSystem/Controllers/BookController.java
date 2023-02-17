package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOS.EntryBookDto;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/addBook")
    public String addBook(@RequestBody() EntryBookDto bookDto){
        return bookService.addBook(bookDto);
    }
}
