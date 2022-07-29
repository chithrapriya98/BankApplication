package com.bank.app.Controller;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

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
public class DepositController {
	@Autowired
	AccountService service;
	@Autowired
	TransactionService transService;

	// deposit page

	@GetMapping("/deposit")
	public String showDepositForm(Model model, HttpSession session) {
		try {
			Transaction trans = new Transaction();
			model.addAttribute("user", trans);
			long id = (long) session.getAttribute("userid");

			AccountDetails user = service.findUserById(id);
			System.out.println(user);

			AccountDetails users = new AccountDetails();
			model.addAttribute("users", users);

			session.setAttribute("username", user.getfirstName());
			session.setAttribute("account", user.getaccountNo());
			session.setAttribute("accounttype", user.getaccountType());
			session.setAttribute("userid", id);

			return "deposit_form";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	// process of deposit page

	@PostMapping("/process_deposit")
	public String processDeposit(@ModelAttribute("user") Transaction deposit, Model model, HttpSession session) {
		try {
			AccountDetails users = new AccountDetails();
			model.addAttribute("users", users);

			AccountDetails s = service.findUserById((long) session.getAttribute("userid"));
			session.setAttribute("obj", s.getfirstName());
			model.addAttribute("object", s);
			double b = deposit.getAmount() + s.getBalance();
			long id = (long) session.getAttribute("userid");
			System.out.println("id=" + id);
			if (deposit.getAmount() == 0
					|| (deposit.getAmount() > 20000 && s.getaccountType().equalsIgnoreCase("Savings Account"))) {
				model.addAttribute("zero",
						"Please, Enter Minimum amount upto 20000.\n The Deposit amount should not be Zero");
				return "deposit_form";
			} else if (b == 500000 && s.getaccountType().equalsIgnoreCase("Savings Account")) {
				model.addAttribute("zero",
						"Sorry,you cannot deposit amount.\n Beacuse your have exceed your account limit  ");

				return "deposit_form";
			} else if (b == 1000000 && s.getaccountType().equalsIgnoreCase("Current Account")) {
				model.addAttribute("zero",
						"Sorry,you cannot deposit amount.\n Beacuse your have exceed your account limit  ");

				return "deposit_form";
			} else if (b != 0) {

				s.setBalance(b);
				AccountDetails sb = service.updatebalance(b, s.getaccountNo(), s);

//					System.out.println("balance of user=" + sb.getBalance());
//					List<AccountDetails> lis = service.findAllUser();
//					lis.forEach(System.out::println);
				Transaction transaction = transService.deposit(sb, b, deposit.getAmount());
				model.addAttribute("deposucess", "true");
				model.addAttribute("deposit", transaction);
				model.addAttribute("time",
						transaction.getTransferTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
				model.addAttribute("name", transaction.getUser().getfirstName());
//					List<Transaction> translist = transService.findAllTransaction();
//					translist.forEach(System.out::println);
				return "transaction_sucess";

			}
//			System.out.println("amount-" + b);
			return "transaction_sucess";

		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

}
