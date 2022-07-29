package com.bank.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.Entity.Transaction;

public interface TransactionRepositroy extends JpaRepository<Transaction, Long>{

}
