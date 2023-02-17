package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOS.IssueTransactionDto;
import com.example.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public String issueBook(@RequestBody() IssueTransactionDto transactionDto){
        try {
            return transactionService.issueRequest(transactionDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
