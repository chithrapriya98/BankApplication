package com.bank.app.Controller;

import java.util.List;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.app.Entity.AccountDetails;
import com.bank.app.Repository.UserRepository;
import com.bank.app.Service.AccountService;
import com.bank.app.WebSecurityConfiguration.CustomUserDetailsService;




@Controller
public class MainController {
	
	
   //Autowired and imported AccountService class
	
	@Autowired
	AccountService service;
	
	//Index page 
	
	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}
	
	//Login error page
	
	@GetMapping("/login_error")
	public String viewLoginError() {
		return "login_error";
	}
	
	//Login page 
	
	@GetMapping("/login")
	public String loginprocess(Model model) {
		AccountDetails user=new AccountDetails();
		model.addAttribute("user", user);
		return "login";
	}
	
	//Validating Login page using spring security UserDetails interface
	
	@PostMapping("/login_process")
	public String loginprocess( AccountDetails user,Model model) {
		
		return "home";
	}
	
	//Get Mapping of register page
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new AccountDetails());
		
		return "register";
	}
	
	//validate the values in register page and save the account details in the database
	
	@PostMapping("/process_register")
	public String processRegister(@ModelAttribute("user")AccountDetails signup,Model model) {
		List<AccountDetails> list = service.findAllUser();
		list.forEach(System.out::println);
		try {
			for (AccountDetails k : list) {
				if ((k.getEmail().equals(signup.getEmail())) && (k.getaccountType().equals(signup.getaccountType()))) {
					model.addAttribute("mail", "Email ID Aleardy Exists");
					return "/register";
				}
				if (((k.getPhone() == signup.getPhone()) && k.getaccountType().equals(signup.getaccountType()))) {
					model.addAttribute("mail", "User Aleardy Aleardy Exists for this type of account");
					return "/register";
				}
			}
			AccountDetails sign = service.save(signup);
			//System.out.println(sign);
			model.addAttribute("username", sign.getuserName());
			model.addAttribute("accountno", sign.getaccountNo());
		} catch (Exception e) {
			//System.out.println(e.toString());
			model.addAttribute("error", e.toString());
			return "register_error";
		}

//		List<AccountDetails> list1 = service.findAllUser();
//		list1.forEach(System.out::println);
		return "register_sucess";

	}
	
	//home page
	
	@GetMapping("/home")
	public String listUsers(Model model,HttpSession session) {
//		System.out.println(CustomUserDetailsService.getUserName());
		AccountDetails user=service.findByEmail(CustomUserDetailsService.getUserName());
		session.setAttribute("name", user.getfirstName());
		session.setAttribute("userid", user.getuserId());
		session.setAttribute("user", user);
		
		
		return "home";
	}
	
	//navigate to forgot_password Modol
	
	@PostMapping("/frogot_password")
  public String forgetPassword(@ModelAttribute("user") AccountDetails signup,HttpSession session,Model model) {
	
  	AccountDetails sign=service.findByEmail(signup.getEmail());
  	//System.out.println("before=>"+sign);
  	if(sign==null) {
  		model.addAttribute("notfound", "Sorry, Cann't find Email-ID \n Please enter correct Email-ID");
  		//System.out.println("null");
  		return "login";
  	}else{
  		model.addAttribute("sign", sign.getEmail());
  		//System.out.println("ok");
  	}
  	return "change_password";
  }
	
	//validates the given password and changes the password in database
  
  @PostMapping("/change")
  public String changePassword(@ModelAttribute("user") AccountDetails signin,Model model) {
  	boolean changed=service.changePassword(signin.getPassword(), signin.getEmail());
  	if(changed==true) {
  		model.addAttribute("success", "changed");
  		return "change_password";
  	}
  	return "change_password";
  }
  
  //Profile page- display all account holder details 
  
  @GetMapping("/profile")
	public String showProfile(Model model,HttpSession session) {
	  try {
		  long user=(long) session.getAttribute("userid");
			AccountDetails sign=service.findUserById(user);
			AccountDetails account = new AccountDetails();
			model.addAttribute("user", account);
			//System.out.println("id"+user);
			//System.out.println(sign);
			session.setAttribute("name", sign.getfirstName());
			session.setAttribute("lastname", sign.getlastName());
			session.setAttribute("dob", sign.getdateOfBirth());
			session.setAttribute("address", sign.getAddress()+",\n"+sign.getCity()+"-"+sign.getpinCode()+",\n"+sign.getState());
			session.setAttribute("email", sign.getEmail());
			session.setAttribute("phone", sign.getPhone());
			session.setAttribute("type", sign.getaccountType());
			session.setAttribute("gender", sign.getGender());
			
			return "profile";
	  }catch (Exception e) {
		// TODO: handle exception
		  return "error";
	}
		
	}
  
  //profile page- provide edit option for mobile number
  
  @PostMapping("/change_mob")
	 public String changeMobile(@ModelAttribute("user") AccountDetails signup,HttpSession session,Model model) {
	  try {
		  AccountDetails s = service.findUserById((long) session.getAttribute("userid"));
			 System.out.println(s);
			 if(signup.getPhone()==0) {
				 model.addAttribute("incorrect", "wrong");
				 return "profile";
			 }
			 AccountDetails u=service.updateMobile(signup.getPhone(), s.getaccountNo(), s);
			 //System.out.println(u);
			 if(u!=null) {
				 model.addAttribute("sucess","hello");
				 session.setAttribute("phone",u.getPhone());
				 return "profile";
			 }else {
				 model.addAttribute("error", "error");
			 }
			 
			 return "profile";
	  }catch (Exception e) {
		// TODO: handle exception
		  return "error";
	}
		 
	 }
  
  
  
	
}
