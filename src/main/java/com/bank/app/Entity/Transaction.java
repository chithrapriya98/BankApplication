package com.bank.app.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="transactiondetails")
public class Transaction {
	@Id
	@Column(length = 15)
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_GEN")
	@SequenceGenerator(name="TRANSACTION_GEN", sequenceName = "TRANSACTION_SEQ",allocationSize = 1)
	private long transactionId;
	@Column(length = 15,nullable = false)
	private Long sourceAccount;
	@Column(length = 15,nullable = false)
	private long targetAccount;
	@Column(length = 200,nullable = false)
	private double amount;
	private LocalDate transferDate;
	private LocalTime transferTime;
	@Column(length = 200,nullable = false)
	private double totalBalance;
	@Column(length = 30,nullable = false)
	private String transferType;
	@Column(length = 30,nullable = false)
	private String sourceAccountType;
	@Column(length = 30,nullable = false)
	private String targetAccountType;
		
		@ManyToOne(targetEntity = AccountDetails.class,cascade = CascadeType.ALL)
		@JoinColumn(name = "userId")
		private AccountDetails user;
		

		public Transaction() {
			super();
		}

		public Transaction(Long sourceAccount, long targetAccount, double amount, LocalDate transferDate,
				LocalTime transferTime, double totalBalance, String transferType, String sourceAccountType,
				String targetAccountType, AccountDetails user) {
			super();
			this.sourceAccount = sourceAccount;
			this.targetAccount = targetAccount;
			this.amount = amount;
			this.transferDate = transferDate;
			this.transferTime = transferTime;
			this.totalBalance = totalBalance;
			this.transferType = transferType;
			this.sourceAccountType = sourceAccountType;
			this.targetAccountType = targetAccountType;
			this.user = user;
		}

		public Transaction(long transactionId, Long sourceAccount, long targetAccount, double amount,
				LocalDate transferDate, LocalTime transferTime, double totalBalance, String transferType,
				String sourceAccountType, String targetAccountType, AccountDetails user) {
			super();
			this.transactionId = transactionId;
			this.sourceAccount = sourceAccount;
			this.targetAccount = targetAccount;
			this.amount = amount;
			this.transferDate = transferDate;
			this.transferTime = transferTime;
			this.totalBalance = totalBalance;
			this.transferType = transferType;
			this.sourceAccountType = sourceAccountType;
			this.targetAccountType = targetAccountType;
			this.user = user;
		}

		public long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(long transactionId) {
			this.transactionId = transactionId;
		}

		public Long getSourceAccount() {
			return sourceAccount;
		}

		public void setSourceAccount(Long sourceAccount) {
			this.sourceAccount = sourceAccount;
		}

		public long getTargetAccount() {
			return targetAccount;
		}

		public void setTargetAccount(long targetAccount) {
			this.targetAccount = targetAccount;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public LocalDate getTransferDate() {
			return transferDate;
		}

		public void setTransferDate(LocalDate transferDate) {
			this.transferDate = transferDate;
		}

		public LocalTime getTransferTime() {
			return transferTime;
		}

		public void setTransferTime(LocalTime transferTime) {
			this.transferTime = transferTime;
		}

		public double getTotalBalance() {
			return totalBalance;
		}

		public void setTotalBalance(double totalBalance) {
			this.totalBalance = totalBalance;
		}

		public String gettransferType() {
			return transferType;
		}

		public void settransferType(String transferType) {
			this.transferType = transferType;
		}

		public String getSourceAccountType() {
			return sourceAccountType;
		}

		public void setSourceAccountType(String sourceAccountType) {
			this.sourceAccountType = sourceAccountType;
		}

		public String getTargetAccountType() {
			return targetAccountType;
		}

		public void setTargetAccountType(String targetAccountType) {
			this.targetAccountType = targetAccountType;
		}

		public AccountDetails getUser() {
			return user;
		}

		public void setUser(AccountDetails user) {
			this.user = user;
		}
		
		@Override
		public String toString() {
			return "Transaction [transactionId=" + transactionId + ", sourceAccount=" + sourceAccount + ", targetAccount="
					+ targetAccount + ", amount=" + amount + ", transferDate=" + transferDate + ", transferTime="
					+ transferTime + ", totalBalance=" + totalBalance + ", transferType=" + transferType
					+ ", sourceAccountType=" + sourceAccountType + ", targetAccountType=" + targetAccountType + ", user="
					+ user + "]";
		}
		
		
}
