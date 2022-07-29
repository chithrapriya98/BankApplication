package com.bank.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.bank.app.Entity.AccountDetails;




public interface UserRepository extends JpaRepository<AccountDetails, Long>{
	@Query("SELECT u FROM AccountDetails u WHERE u.email = ?1")
	public AccountDetails findByEmail(String email);
	
	@Query("SELECT u FROM AccountDetails u WHERE u.accountNo = ?1")
	public AccountDetails findByAccountNo(Long accountno);
}
