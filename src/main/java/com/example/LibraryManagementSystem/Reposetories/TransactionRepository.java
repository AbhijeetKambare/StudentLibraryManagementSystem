package com.example.LibraryManagementSystem.Reposetories;

import com.example.LibraryManagementSystem.Models.Transactions;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId and is_issued_operation=true ORDER BY id DESC", nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId,int cardId);
    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId and is_issued_operation=true and transaction_status='SUCCESS'", nativeQuery = true)
    Transactions getissuedTransaction(int bookId,int cardId);
    //it willgive you latest transaction ffor this credintials

}
