package com.bank.app.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.Entity.AccountDetails;
import com.bank.app.Entity.Transaction;
import com.bank.app.Repository.TransactionRepositroy;




@Service
public class TransactionService {
	
	@Autowired
	TransactionRepositroy repo;

	public List<Transaction> findAllTransaction() {
		return repo.findAll();
	}

	public List<Transaction> findTransactionByUserId(long id) {
		List<Transaction> viewList = new ArrayList<Transaction>();

		for (Transaction k : findAllTransaction()) {
			if (k.getUser().getuserId() == id) {
				viewList.add(new Transaction(k.getTransactionId(), k.getSourceAccount(), k.getTargetAccount(), k.getAmount(), k.getTransferDate(), k.getTransferTime(), k.getTotalBalance(), k.gettransferType(), k.getSourceAccountType(), k.getTargetAccountType(), k.getUser()));
			}
		}

		return viewList;
	}

	
	public Transaction deposit(AccountDetails sign,double totalAmount,double amount) {
		Transaction trans=new Transaction(sign.getaccountNo(),sign.getaccountNo() , amount, LocalDate.now(), LocalTime.now(), totalAmount,"Deposit" ,sign.getaccountType(), sign.getaccountType(), sign);
		return repo.save(trans);
	}
	public Transaction withdraw(AccountDetails sign,double totalAmount,double amount) {
		Transaction trans=new Transaction(sign.getaccountNo(),sign.getaccountNo() , amount, LocalDate.now(), LocalTime.now(), totalAmount,"withdraw" ,sign.getaccountType(), sign.getaccountType(), sign);
		return repo.save(trans);
	}
	public Transaction senderTransfer(AccountDetails sign,double totalAmount,double amount,AccountDetails reciver) {
		Transaction trans=new Transaction(sign.getaccountNo(),reciver.getaccountNo() , amount, LocalDate.now(), LocalTime.now(), totalAmount,"transfered to" ,sign.getaccountType(), reciver.getaccountType(), sign);
		return repo.save(trans);
	}
	public Transaction reciverTransfer(AccountDetails sign,double totalAmount,double amount,AccountDetails sender) {
		Transaction trans=new Transaction(sign.getaccountNo(),sender.getaccountNo() , amount, LocalDate.now(), LocalTime.now(), totalAmount,"transfered from" ,sign.getaccountType(), sender.getaccountType(), sign);
		return repo.save(trans);
	}
}
