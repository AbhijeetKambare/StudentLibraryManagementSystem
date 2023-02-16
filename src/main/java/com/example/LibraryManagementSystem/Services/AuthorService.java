package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.DTOS.EntryAuthorDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Reposetories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
