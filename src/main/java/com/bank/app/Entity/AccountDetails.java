package com.bank.app.Entity;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="accountdetails")
public class AccountDetails {

		@Id
		@Column(length = 15)
//		@GeneratedValue(strategy = GenerationType.AUTO)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNTDETAILS_GEN")
		@SequenceGenerator(name="ACCOUNTDETAILS_GEN", sequenceName = "ACCOUNTDETAILS_SEQ" ,allocationSize = 1)
		private long userId;
		@Column(unique = true,length = 15 ,nullable = false)
		private Long accountNo;
		@Column(length = 60,nullable = false)
		private String firstName;
		@Column(length = 60,nullable = false)
		private String lastName;
		private Date dateOfBirth;
		@Column(length = 20,nullable = false)
		private String gender;
		@Column(length = 200,nullable = false)
		private String address;
		@Column(length = 15,nullable = false)
		private int pinCode;
		@Column(length = 60,nullable = false)
		private String city;
		@Column(length = 60,nullable = false)
		private String state;
		@Column(unique = true,length = 70,nullable = false)
		private String email;
		@Column(unique = true,length = 16,nullable = false)
		private long phone;
		@Column(length = 16,nullable = false)
		private long aadharNo;
		@Column(length = 20,nullable = false)
		private String panNo;
		@Column(length = 60)
		private String userName;
		@Column(length = 60,nullable = false)
		private String password;
		@Column(length = 30,nullable = false)
		private String accountType;
		@Column(length = 200,nullable = false)
		private double balance;
		
		
		
		public AccountDetails() {
			super();
		}
		

		
		



		public AccountDetails(long userId, String userName, String password) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.password = password;
		}







		public AccountDetails(long userId, Long accountNo, String firstName, String lastName, Date dateOfBirth, String gender,
				String address, int pinCode, String city, String state, String email, long phone, long aadharNo, String panNo,
				String userName, String password, String accountType, double balance) {
			super();
			this.userId = userId;
			this.accountNo = accountNo;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.address = address;
			this.pinCode = pinCode;
			this.city = city;
			this.state = state;
			this.email = email;
			this.phone = phone;
			this.aadharNo = aadharNo;
			this.panNo = panNo;
			this.userName = userName;
			this.password = password;
			this.accountType = accountType;
			this.balance = balance;
		}
		


		public AccountDetails(Long accountNo, String firstName, String lastName, Date dateOfBirth, String gender, String address,
				int pinCode, String city, String state, String email, long phone, long aadharNo, String panNo,
				String userName, String password, String accountType, double balance) {
			super();
			this.accountNo = accountNo;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.address = address;
			this.pinCode = pinCode;
			this.city = city;
			this.state = state;
			this.email = email;
			this.phone = phone;
			this.aadharNo = aadharNo;
			this.panNo = panNo;
			this.userName = userName;
			this.password = password;
			this.accountType = accountType;
			this.balance = balance;
		}




		

		public AccountDetails(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}







		public long getuserId() {
			return userId;
		}



		public void setuserId(long userId) {
			this.userId = userId;
		}



		public Long getaccountNo() {
			return accountNo;
		}



		public void setaccountNo(Long accountNo) {
			this.accountNo = accountNo;
		}



		public String getfirstName() {
			return firstName;
		}



		public void setfirstName(String firstName) {
			this.firstName = firstName;
		}



		public String getlastName() {
			return lastName;
		}



		public void setlastName(String lastName) {
			this.lastName = lastName;
		}



		public Date getdateOfBirth() {
			return dateOfBirth;
		}



		public void setdateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}



		public String getGender() {
			return gender;
		}



		public void setGender(String gender) {
			this.gender = gender;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public int getpinCode() {
			return pinCode;
		}



		public void setpinCode(int pinCode) {
			this.pinCode = pinCode;
		}



		public String getCity() {
			return city;
		}



		public void setCity(String city) {
			this.city = city;
		}



		public String getState() {
			return state;
		}



		public void setState(String state) {
			this.state = state;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public long getPhone() {
			return phone;
		}



		public void setPhone(long phone) {
			this.phone = phone;
		}



		public long getaadharNo() {
			return aadharNo;
		}



		public void setaadharNo(long aadharNo) {
			this.aadharNo = aadharNo;
		}



		public String getpanNo() {
			return panNo;
		}



		public void setpanNo(String panNo) {
			this.panNo = panNo;
		}



		public String getuserName() {
			return userName;
		}



		public void setuserName(String userName) {
			this.userName = userName;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getaccountType() {
			return accountType;
		}



		public void setaccountType(String accountType) {
			this.accountType = accountType;
		}



		public double getBalance() {
			return balance;
		}



		public void setBalance(double balance) {
			this.balance = balance;
		}









		@Override
		public String toString() {
			return "Signup [userId=" + userId + ", accountNo=" + accountNo + ", firstName=" + firstName + ", lastName="
					+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", address=" + address + ", pinCode=" + pinCode
					+ ", city=" + city + ", state=" + state + ", email=" + email + ", phone=" + phone + ", aadharNo=" + aadharNo
					+ ", panNo=" + panNo + ", userName=" + userName + ", password=" + password + ", accountType="
					+ accountType + ",Balance="+balance+"]";
		}
		
		
		
	

}
