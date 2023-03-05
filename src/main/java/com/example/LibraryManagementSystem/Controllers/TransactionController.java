package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOS.IssueTransactionDto;
import com.example.LibraryManagementSystem.DTOS.getTransactionBCardDto;
import com.example.LibraryManagementSystem.DTOS.returnBookTransactionDto;
import com.example.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getTxnInfo")
    public List<String> getTransactionEntry(@RequestBody() IssueTransactionDto transactionDto){
        //System.out.println(bookId+"thi si book id "+cardId +"thi is card id");
        return transactionService.getTransactions(transactionDto);
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestBody()returnBookTransactionDto returnBookTransactionDto){
        try {
            return transactionService.returnBook(returnBookTransactionDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
