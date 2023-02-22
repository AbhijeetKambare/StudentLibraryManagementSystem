package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOS.EntryAuthorDto;
import com.example.LibraryManagementSystem.DTOS.RequestAuthorDto;
import com.example.LibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Madhuri")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody() EntryAuthorDto autherDtoRequest){
        System.out.println("author");
        return authorService.createAuthor(autherDtoRequest);
    }
    @GetMapping("/Get")
    public RequestAuthorDto getAuthor(@RequestParam("id") int authorId){
        return authorService.getAuthor(authorId);
    }
}
