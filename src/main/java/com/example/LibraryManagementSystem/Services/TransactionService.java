package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Controllers.TransactionController;
import com.example.LibraryManagementSystem.DTOS.IssueTransactionDto;
import com.example.LibraryManagementSystem.DTOS.getTransactionBCardDto;
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

import java.util.ArrayList;
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
    public String issueRequest(IssueTransactionDto issueBookRequestDto) throws Exception{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the Book Entity and Card Entity ??? Why do we need this
        //We are this bcz we want to set the Transaction attributes...

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();


        //Final goal is to make a transaction Entity, set its attribute
        //and save it.
        Transactions transaction = new Transactions();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssuedOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        //attribute left is success/Failure
        //Check for validations
        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");

        }

        if(card==null || (card.getCardStatus()!=CardStatus.ACTIVATED)){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new Exception("Card is not valid");
        }



        //We have reached a success case now.

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        //set attributes of book
        book.setIssued(true);
        //Btw the book and transaction : bidirectional
        List<Transactions> listOfTransactionForBook = book.getListOfTransaction();
        listOfTransactionForBook.add(transaction);
        book.setListOfTransaction(listOfTransactionForBook);


        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);


        //Card and the Transaction : bidirectional (parent class)
        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);


        //save the parent.
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.
        //Saving the parent

        //automatically book is  dparent will be updated due to cacading effect of bidirectional

        return "Book issued successfully";
    }
    public List<String> getTransactions(IssueTransactionDto transactionDto){
        int bookId= transactionDto.getBookId();
        int cardId= transactionDto.getCardId();

        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        List<String> listtransactions=new ArrayList<>();
        for (Transactions transactions:transactionsList){
            listtransactions.add(transactions.getTransactionId());
        }
        //String transactionId = transactionsList.get(0).getTransactionId();

        return listtransactions;
    }

}
