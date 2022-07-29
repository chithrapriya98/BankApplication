package com.bank.app.Controller;

import java.security.Signer;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.app.Entity.AccountDetails;
import com.bank.app.Entity.Transaction;
import com.bank.app.Service.AccountService;
import com.bank.app.Service.TransactionService;

@Controller
public class TransferController {

	@Autowired
	AccountService service;
	@Autowired
	TransactionService transService;

	// Transfer page

	@GetMapping("/transfer")
	public String showTransferForm(Model model, HttpSession session) {
		try {
			Transaction trans = new Transaction();
			model.addAttribute("user", trans);
			long id = (long) session.getAttribute("userid");

			AccountDetails user = service.findUserById(id);
			// System.out.println(user);

			session.setAttribute("username", user.getfirstName());
			session.setAttribute("account", user.getaccountNo());
			session.setAttribute("accounttype", user.getaccountType());
			session.setAttribute("userid", id);

			return "transfer_form";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	// validating and adding transaction details into database

	@PostMapping("process_transfer")
	public String processTransfer(@ModelAttribute("user") Transaction transaction, Model model, HttpSession session) {
		try {
			AccountDetails sender = service.findUserById((long) session.getAttribute("userid"));
			AccountDetails reciver = service.findByAccountno(transaction.getTargetAccount());
			if (reciver == null) {
				model.addAttribute("zero", "Sorry, Please Enter the correct Account Number to Transfer");
				return "transfer_form";
			}
			double senderBalance = sender.getBalance() - transaction.getAmount();
			double reciverBalance = reciver.getBalance() + transaction.getAmount();
			if (senderBalance < 0) {
				model.addAttribute("zero",
						"Sorry, You can't Transfer amount because No such Balance in your Account...!");
				return "transfer_form";
			}
			if (reciver.getaccountType().equalsIgnoreCase("Savings Account") && reciverBalance > 500000) {
				model.addAttribute("zero",
						"Sorry, you can't transfer amount beacuse the receiver Account has exceed the limit");
				return "transfer_form";
			}
			if (reciver.getaccountType().equalsIgnoreCase("Current Account") && reciverBalance > 1000000) {
				model.addAttribute("zero",
						"Sorry, you can't transfer amount beacuse the receiver Account has exceed the limit");
				return "transfer_form";
			}
			AccountDetails send = service.updatebalance(senderBalance, sender.getaccountNo(), sender);
			AccountDetails recieve = service.updatebalance(reciverBalance, reciver.getaccountNo(), reciver);
			Transaction senderTrans = transService.senderTransfer(sender, senderBalance, transaction.getAmount(),
					recieve);
			Transaction reciverTrans = transService.reciverTransfer(reciver, reciverBalance, transaction.getAmount(),
					send);
			model.addAttribute("time",
					senderTrans.getTransferTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
			model.addAttribute("deposit", senderTrans);
			model.addAttribute("sname", senderTrans.getUser().getfirstName());
			model.addAttribute("uname", reciverTrans.getUser().getfirstName());
			model.addAttribute("transsucess", "true");
			return "transaction_sucess";
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return "error";
		}

	}
}
