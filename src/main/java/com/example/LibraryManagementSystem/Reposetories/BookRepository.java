package com.example.LibraryManagementSystem.Reposetories;

import com.example.LibraryManagementSystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
