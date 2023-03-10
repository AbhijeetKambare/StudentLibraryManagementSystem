package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Controllers.TransactionController;
import com.example.LibraryManagementSystem.DTOS.IssueTransactionDto;
import com.example.LibraryManagementSystem.DTOS.getTransactionBCardDto;
import com.example.LibraryManagementSystem.DTOS.returnBookTransactionDto;
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

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public String returnBook(returnBookTransactionDto dto)throws Exception{
        int bookId=dto.getBookId();
        int cardId=dto.getCardId();
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

        Transactions transactions=new Transactions();
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssuedOperation(false);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setTransactionStatus(TransactionStatus.PENDING);


        if (book==null ||book.isIssued()==false){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("book is already returned");
        }
        //success case
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        //calculate fine
        int fine=calculateFine(bookId,cardId);
        transactions.setFine(fine);
        book.setIssued(false);

        List<Transactions> transactionsListOfBook=book.getListOfTransaction();
        transactionsListOfBook.add(transactions);
        book.setListOfTransaction(transactionsListOfBook);

        List<Book> books=card.getBooksIssued();
        for(Book book1:books){
            if(book1.getId()==bookId)
                books.remove(book1);
        }
        card.setBooksIssued(books);

        List<Transactions> transactionsListofCard=card.getTransactionsList();
        transactionsListofCard.add(transactions);
        card.setTransactionsList(transactionsListofCard);

        //we need to rmove issued book from issuedbooklist

        transactionRepository.save(transactions);
        //if ewe save parents child willget automatically saved
        cardRepository.save(card);
        return "Book returned succcesfully with fine "+fine;
    }
    public int calculateFine(int bookId,int cardId){
        List<Transactions> transactionsList=null;
        try {
             transactionsList= transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        }catch (Exception e){
            System.out.println( "something is wrong in transction details ");
        }
        Transactions transactions=null;
        if(transactionsList!=null) {
            for (Transactions transactionsitr : transactionsList) {
                if (transactionsitr.isIssuedOperation() == true && transactionsitr.getTransactionStatus().equals(TransactionStatus.SUCCESS)) {
                        transactions=transactionsitr;
                        break;
                }
            }
        }
       Date issueDate=transactions.getTransactionDate();//this is transaction date so need to convert inlocal date  format
       LocalDate currentDate= LocalDate.now(); //this is todays date in formate of local date
        int actualFine=0;
        int lateFine=2;

        //converting Date to localDate
        LocalDate issuedDateInLocal=issueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //calculate period between that days;
        Period period=Period.between(issuedDateInLocal,currentDate);
        int days= period.getDays();
        if(days<=7) return actualFine;
        else {
            days=days-7;
            if(days%7==0){
                actualFine=days/7*lateFine*7;
            }else {
                int completeDays=days/7;
                int rem=days%7;
                actualFine=rem*lateFine+completeDays*lateFine*7;
            }
        }
        return actualFine;
    }

}
