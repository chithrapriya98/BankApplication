package com.bank.app.WebSecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bank.app.Entity.AccountDetails;
import com.bank.app.Repository.UserRepository;


public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountDetails user = userRepo.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

	
	 public static String getUserName() {
	        SecurityContext securityContext = SecurityContextHolder.getContext();
	        Authentication authentication = securityContext.getAuthentication();
	        String userName = null;
	        if (authentication != null) {

	                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	                userName = userDetails.getUsername();

	        }
	        return userName;
	    }
}
