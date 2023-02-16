package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.DTOS.EntryBookDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Reposetories.AuthorRepository;
import com.example.LibraryManagementSystem.Reposetories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book){
        //we setted basic attribute from postmon
        //we are passing in auther object one auther's id by using that we are updating list of books of authors







        //this is if we regular without DTO passing two obj
        int authorId =book.getAuthor().getId();
//we fecthed idof auther from postmon object of auther which included in book object of postman
        Author author=authorRepository.findById(authorId).get();
        //fetched the auther
        book.setAuthor(author);//foreign key attribute set
        //set the author in child class
        List<Book> writtenBooks=author.getBooksWritten();
        //list of books inparent class we are updating
        writtenBooks.add(book);
        authorRepository.save(author);//if we saved the auther then book will automaticalyy savedcascading effect
        return "book added succesfully";
    }
}
