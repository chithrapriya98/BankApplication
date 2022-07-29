package com.bank.app.Service;

import java.util.List;



import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.app.Entity.AccountDetails;
import com.bank.app.Repository.UserRepository;








@Service
public class AccountService {
	
	@Autowired
	UserRepository repo;
	
	
	public AccountDetails save(AccountDetails sign) {
		System.out.println("hello Service");
		try {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(sign.getPassword());
			sign.setPassword(encodedPassword);
			Long unique = (long) Math.floor(Math.random() * 9000000000L);
			sign.setaccountNo(unique);
			sign.setBalance(0);
//			sign.setPassword(encode.encode(sign.getPassword()));
			sign=repo.save(sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sign;
	}


public boolean changePassword(String pass,String email) {
		
		AccountDetails sign=repo.findByEmail(email);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(pass);
		sign.setPassword(encodedPassword);
		sign.setuserId(sign.getuserId());
		repo.save(sign);
		System.out.println("after=>"+sign);
		return true;
	}


public AccountDetails findByEmail(String email) {
	return repo.findByEmail(email);
}


public AccountDetails findUserById(long id) {
	Optional<AccountDetails> sign=repo.findById(id);
	return sign.get();
}


public List<AccountDetails> findAllUser() {
	 List<AccountDetails> listCustomers = repo.findAll();
	 return listCustomers;
}


public AccountDetails updatebalance(double balance,long accountno,AccountDetails s) {
	s.setBalance(balance);
	s.setaccountNo(accountno);
	AccountDetails sign=repo.save(s);
	return sign;
}
public AccountDetails updateMobile(long phone,long accountno,AccountDetails s) {
	s.setPhone(phone);
	s.setaccountNo(accountno);
	AccountDetails sign=repo.save(s);
	return sign;
}

public AccountDetails findByAccountno(Long accountNo) {
	return repo.findByAccountNo(accountNo);
}


}
