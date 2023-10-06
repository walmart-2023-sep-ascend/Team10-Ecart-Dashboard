package com.wm.ECartPGPTeamTen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wm.ECartPGPTeamTen.security.ECartPgpJwtTokenGenerator;
import com.wm.ECartPGPTeamTen.vo.UserDetailsVO;

/**
 * @author Raju
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/ecarto")
public class EcartPgpJwtAuthenticationController {


	@Autowired
	ECartPgpJwtTokenGenerator jwtTokenGenerator;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken1(@RequestBody UserDetailsVO authenticationRequestEmail)
			throws Exception {
		ResponseEntity<?> res = jwtTokenGenerator.authenticate(authenticationRequestEmail.getEmail());
		 return res;
	}
}
