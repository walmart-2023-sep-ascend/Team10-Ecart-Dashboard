package com.wm.ECartPGPTeamTen.security;

import java.util.Calendar;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wm.ECartPGPTeamTen.util.ECartPGPJwtTokenUtil;
import com.wm.ECartPGPTeamTen.vo.EcartJwtResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ECartPgpJwtTokenGenerator {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ECartPGPJwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	
	public String authenticateToken(String userName) throws Exception {
		
		log.info("Token Verificatoin for :"+userName);
		long mUnique = Calendar.getInstance().getTimeInMillis();
		String pwd = new BCryptPasswordEncoder().encode(userName);
		authenticate(userName, userName + "");

		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(userName);

		final String token = jwtTokenUtil.generateToken(userDetails);
		return "ecart" + token;
	}
	
	public ResponseEntity<?> authenticate(String userName) throws Exception {
		return ResponseEntity.ok(new EcartJwtResponse(authenticateToken(userName)));
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
}
