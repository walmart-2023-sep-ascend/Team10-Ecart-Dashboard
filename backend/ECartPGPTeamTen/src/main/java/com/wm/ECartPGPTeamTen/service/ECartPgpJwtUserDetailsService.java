package com.wm.ECartPGPTeamTen.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.exception.ResourceNotFoundException;
import com.wm.ECartPGPTeamTen.model.UserModel;

@Service
public class ECartPgpJwtUserDetailsService  implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(ECartPgpJwtUserDetailsService.class);
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel uservo = null;
		try {
			uservo = userService.getUserDetailsEmail(username).get();
		} catch (NoSuchElementException | ResourceNotFoundException e) {
			e.printStackTrace();
			logger.error("error occured");
			throw new UsernameNotFoundException("User not found with username: " + username);
		} catch (ECartException e) {
			e.printStackTrace();
			logger.error("error occured");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		if (username != null && !username.isEmpty() && uservo != null && uservo.getId() != null) {
				String pwd = new BCryptPasswordEncoder().encode(username);
				UserDetails user = new User(username, pwd,
						new ArrayList<>());
				String role ="user";
				if("admin".equalsIgnoreCase(username)) {
					role = messageService.getMessage("ecart.adminrole");
				}else {
					role = messageService.getMessage("ecart.userrole");
				}
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
			       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
			
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
