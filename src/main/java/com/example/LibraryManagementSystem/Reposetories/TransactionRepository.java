package com.example.LibraryManagementSystem.Reposetories;

import com.example.LibraryManagementSystem.Models.Transactions;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
}
