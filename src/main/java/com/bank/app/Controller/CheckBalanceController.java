package com.bank.app.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
public class CheckBalanceController {

	@Autowired
	AccountService service;

	@Autowired
	TransactionService transService;

	@GetMapping("/balance")
	public String showcheckbalance(Model model, HttpSession session) {
		try {
			long id = (long) session.getAttribute("userid");

			AccountDetails user = new AccountDetails();
			model.addAttribute("user", user);
			return "check_balance";
		} catch (Exception e) {
			// TODO: handle exception

			return "error";
		}

	}

	@PostMapping("/check_process")
	public String checkBalance(@ModelAttribute("user") AccountDetails signup, HttpSession session, Model model) {

		try {

			long id = (long) session.getAttribute("userid");
			AccountDetails sign = service.findUserById(id);
			if (BCrypt.checkpw(signup.getPassword(), sign.getPassword())) {
				model.addAttribute("balance", sign.getBalance());
				return "check_balance";
			} else {
				model.addAttribute("error", "Please Enter the Correct password");
				return "check_balance";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	@GetMapping("/statement")
	public String showStatement(Model model, HttpSession session) {
		try {
			long id = (long) session.getAttribute("userid");
			List<Transaction> list = transService.findTransactionByUserId(id);
			List<Transaction> deposit = new ArrayList<Transaction>();
			List<Transaction> withdraw = new ArrayList<Transaction>();
			List<Transaction> transffrom = new ArrayList<Transaction>();
			List<Transaction> transto = new ArrayList<Transaction>();
			for (Transaction k : list) {
				if (k.gettransferType().equalsIgnoreCase("Deposit")) {
					deposit.add(k);
				} else if (k.gettransferType().equalsIgnoreCase("withdraw")) {
					withdraw.add(k);
				} else if (k.gettransferType().equalsIgnoreCase("transfered from")) {
					transffrom.add(k);
				} else if (k.gettransferType().equalsIgnoreCase("transfered to")) {
					transto.add(k);
				}
			}
			model.addAttribute("dlist", deposit);
			model.addAttribute("wlist", withdraw);
			model.addAttribute("tlist", transffrom);
			model.addAttribute("ttolist", transto);

			return "statement";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}
}
