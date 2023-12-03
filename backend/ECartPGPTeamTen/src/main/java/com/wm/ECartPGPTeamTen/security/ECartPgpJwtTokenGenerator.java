/*package com.wm.ECartPGPTeamTen.security;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.exception.ResourceNotFoundException;
import com.wm.ECartPGPTeamTen.model.UserModel;
import com.wm.ECartPGPTeamTen.service.UserService;
import com.wm.ECartPGPTeamTen.util.ECartPGPJwtTokenUtil;
import com.wm.ECartPGPTeamTen.vo.EcartJwtResponse;
import com.wm.ECartPGPTeamTen.vo.ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ECartPgpJwtTokenGenerator {

	private static final Logger logger = LoggerFactory.getLogger(ECartPgpJwtTokenGenerator.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ECartPGPJwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	
	public EcartJwtResponse authenticateToken(String userName) throws Exception {
		
		log.info("Token Verificatoin for :"+userName);
		//long mUnique = Calendar.getInstance().getTimeInMillis();
		//String pwd = new BCryptPasswordEncoder().encode(userName);
		authenticate(userName, userName + "");

		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(userName);

		final String token = jwtTokenUtil.generateToken(userDetails);
		UserModel uservo = null;
		try {
			uservo = userService.getUserDetailsEmail(userName).get();
		} catch (NoSuchElementException | ResourceNotFoundException e) {
			e.printStackTrace();
			logger.error("error occured");
			throw new UsernameNotFoundException("User not found with username: " + userName);
		} catch (ECartException e) {
			e.printStackTrace();
			logger.error("error occured");
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		if(!(uservo != null && uservo.getId() != null)) {
			logger.error("No data");
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		
		EcartJwtResponse res = new EcartJwtResponse("ecart" + token,uservo.getId(),uservo.getEmail());
		return res;
	}
	
	public EcartJwtResponse authenticate(String userName) throws Exception {
		
		
		return authenticateToken(userName);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		//Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.error("22222@@DisabledException");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("@@BadCredentialsException");
			e.printStackTrace();
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
} */
