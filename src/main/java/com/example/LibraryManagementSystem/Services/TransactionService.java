package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Controllers.TransactionController;
import com.example.LibraryManagementSystem.DTOS.IssueTransactionDto;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Transactions;
import com.example.LibraryManagementSystem.Reposetories.BookRepository;
import com.example.LibraryManagementSystem.Reposetories.CardRepository;
import com.example.LibraryManagementSystem.Reposetories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    public String issueRequest(IssueTransactionDto transacxtionDto) throws Exception{

        return "Book issued successfully";
    }
}
