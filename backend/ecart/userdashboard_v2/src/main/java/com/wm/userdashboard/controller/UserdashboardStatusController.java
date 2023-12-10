package com.wm.userdashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wm.userdashboard.service.MessageService;



/**
 * r0m09yu
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserdashboardStatusController {

	private static final Logger logger = LoggerFactory.getLogger(UserdashboardStatusController.class);

	@Autowired
	private Environment env;
	
	@Autowired
	MessageService messageService;
	
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ResponseEntity<Object> createProduct() {

		logger.info("Active profile is :"+env.getProperty("spring.config.activate.on-profile"));
		logger.info("Message Service is :"+messageService.getMessage("brandName"));
		logger.info("Test service is working successfully...!");
		return new ResponseEntity<>("Test service is working successfully", HttpStatus.OK);
	}
}
