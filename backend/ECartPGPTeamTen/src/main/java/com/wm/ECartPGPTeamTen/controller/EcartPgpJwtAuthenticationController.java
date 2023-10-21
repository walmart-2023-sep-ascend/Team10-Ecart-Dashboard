package com.wm.ECartPGPTeamTen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wm.ECartPGPTeamTen.security.ECartPgpJwtTokenGenerator;
import com.wm.ECartPGPTeamTen.vo.EcartJwtResponse;
import com.wm.ECartPGPTeamTen.vo.ResponseVO;
import com.wm.ECartPGPTeamTen.vo.UserDetailsVO;

/**
 * @author Raju
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/ecarto")
public class EcartPgpJwtAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(EcartPgpJwtAuthenticationController.class);

	@Autowired
	ECartPgpJwtTokenGenerator jwtTokenGenerator;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO> createAuthenticationToken1(@RequestBody UserDetailsVO authenticationRequestEmail)
			throws Exception {
		logger.error("Email id :" + authenticationRequestEmail.getEmail());
		
		ResponseVO vo = new ResponseVO();
		try {
			EcartJwtResponse res = jwtTokenGenerator.authenticate(authenticationRequestEmail.getEmail());
			vo.setBody(res);
			vo.setCode(HttpStatus.OK.value());
			vo.setMessage("SUCCESS");
			return ResponseEntity.ok().body(vo);

		} catch (Exception e) {
			vo.setBody(e.getMessage());
			vo.setCode(HttpStatus.UNAUTHORIZED.value());
			vo.setMessage("SUCCESS");
			return ResponseEntity.ok().body(vo);
		}
	}
}
