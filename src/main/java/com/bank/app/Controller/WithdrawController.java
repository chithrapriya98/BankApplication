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
public class WithdrawController {

	@Autowired
	AccountService service;
	@Autowired
	TransactionService transService;

	// Withdraw page

	@GetMapping("/withdraw")
	public String showWithdrawForm(Model model, HttpSession session) {
		try {
			Transaction trans = new Transaction();
			model.addAttribute("user", trans);
			long id = (long) session.getAttribute("userid");

			AccountDetails user = service.findUserById(id);
			System.out.println(user);

			session.setAttribute("username", user.getfirstName());
			session.setAttribute("account", user.getaccountNo());
			session.setAttribute("accounttype", user.getaccountType());
			session.setAttribute("userid", id);

			return "withdrawal_form";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	// process of withdraw page

	@PostMapping("process_withdrawal")
	public String processWithdraw(@ModelAttribute("user") Transaction withdraw, Model model, HttpSession session) {
		try {
			AccountDetails s = service.findUserById((long) session.getAttribute("userid"));
			System.out.println("balance" + s.getBalance());
			if (s.getBalance() == 0 || withdraw.getAmount() == 0) {
				model.addAttribute("zero", "Sorry, Your Minimum Balance is 0 you can't withdraw amount");
				return "withdrawal_form";
			}
			double b = s.getBalance() - withdraw.getAmount();
			if (b < 0) {
				model.addAttribute("zero", "Sorry, You Cann't Withdraw amount");
				return "withdrawal_form";
			}
			System.out.println("after:" + b);
			s.setBalance(b);
			AccountDetails sb = service.updatebalance(b, s.getaccountNo(), s);
			System.out.println("balance of user=" + sb.getBalance());
			Transaction transaction = transService.withdraw(sb, b, withdraw.getAmount());
			model.addAttribute("withdraw", transaction);

			model.addAttribute("time",
					transaction.getTransferTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
			model.addAttribute("name", transaction.getUser().getfirstName());
			List<Transaction> translist = transService.findAllTransaction();
			translist.forEach(System.out::println);

			model.addAttribute("withsucess", "true");
			return "transaction_sucess";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

//		return "withdrawal_sucess";
	}
}
