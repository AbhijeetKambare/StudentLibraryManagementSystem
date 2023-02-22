package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.DTOS.EntryAuthorDto;
import com.example.LibraryManagementSystem.DTOS.RequestAuthorDto;
import com.example.LibraryManagementSystem.DTOS.RequestBooksDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Reposetories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String createAuthor(EntryAuthorDto autherDtoRequest){
        //we are converting the athurDto object to author object by settig ist attribute which user passed by postman in autherDto
        Author author =new Author();
        author.setAuthorName(autherDtoRequest.getAuthorName());
        author.setAge(autherDtoRequest.getAge());
        author.setCountry(autherDtoRequest.getCountry());
        author.setRating(autherDtoRequest.getRating());
        authorRepository.save(author);
        return "Auther added Succesfully";
    }
    public RequestAuthorDto getAuthor(int authorId){
        Author author=authorRepository.findById(authorId).get();
        RequestAuthorDto requestAuthorDto=new RequestAuthorDto();
        requestAuthorDto.setAge(author.getAge());
        requestAuthorDto.setAuthorName(author.getAuthorName());
        requestAuthorDto.setId(author.getId());
        requestAuthorDto.setCountry(author.getCountry());
        requestAuthorDto.setRating(author.getRating());
        List<Book> bookList=author.getBooksWritten();
        List<RequestBooksDto> booksDtoList=new ArrayList<>();
        for(Book book:bookList){
            RequestBooksDto requestBooksDto= new RequestBooksDto();
            requestBooksDto.setBookName(book.getBookName());
            requestBooksDto.setGenre(book.getGenre());
            requestBooksDto.setPages(book.getPages());
            requestBooksDto.setId(book.getId());
            booksDtoList.add(requestBooksDto);
        }
        requestAuthorDto.setBooksDtoList(booksDtoList);
        return requestAuthorDto;
    }
}
