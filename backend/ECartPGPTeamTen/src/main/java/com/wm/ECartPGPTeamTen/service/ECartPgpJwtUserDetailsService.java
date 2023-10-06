package com.wm.ECartPGPTeamTen.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ECartPgpJwtUserDetailsService  implements UserDetailsService{

	@Autowired
	MessageService messageService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username != null && !username.isEmpty()) {
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
