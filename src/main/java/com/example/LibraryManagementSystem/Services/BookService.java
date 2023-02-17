package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.DTOS.EntryBookDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Reposetories.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    //previously it was passing two objects by postman but updated using dto read comments
    public String addBook(EntryBookDto bookDto){
        //we setted basic attribute from postmon
        //we are passing in auther object one auther's id by using that we are updating list of books of authors

//this is if regular without DTO passing two objects in postman which is first line secod line is used by DTO
        //int authorId =book.getAuthor().getId();
        int authorId=bookDto.getAuthorId();
        //we fecthed idof auther from postmon object of auther which included in book object of postman

        Author author=authorRepository.findById(authorId).get();
        //fetched the auther

        Book book=new Book();//we do beacause in dto need object created for book and seeting it using bootDto data
        book.setBookName(bookDto.getBookName());
        book.setGenre(bookDto.getGenre());
        book.setIssued(false);
        book.setPages(bookDto.getPages());

        book.setAuthor(author);//foreign key attribute set
        //set the author in child class
        List<Book> writtenBooks=author.getBooksWritten();
        //list of books inparent class we are updating
        writtenBooks.add(book);
        authorRepository.save(author);//if we saved the auther then book will automaticalyy savedcascading effect
        return "book added succesfully";
    }
}
