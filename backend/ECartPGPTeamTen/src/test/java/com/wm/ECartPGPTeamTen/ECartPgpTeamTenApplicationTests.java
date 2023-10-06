package com.wm.ECartPGPTeamTen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wm.ECartPGPTeamTen.service.MessageService;

/**
 * r0m09yu
 */


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
class ECartPgpTeamTenApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ECartPgpTeamTenApplicationTests.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	MessageService messageService;
	
	@Test
	void test() {
		logger.info("Active profile is :"+env.getProperty("spring.profiles"));
		logger.info("Message Service is :"+messageService.getMessage("brandName"));
		assertTrue(true);
	}

}
