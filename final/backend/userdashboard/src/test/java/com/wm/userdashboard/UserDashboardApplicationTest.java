package com.wm.userdashboard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wm.userdashboard.service.MessageService;



/**
 * r0m09yu
 */



class UserDashboardApplicationTest extends BaseTest{

	private static final Logger logger = LoggerFactory.getLogger(UserDashboardApplicationTest.class);
	
	@InjectMocks
	MessageService messageService;
	
	@Test
	void test() {
		logger.info("Active profile is :");
		logger.info("Message Service is :");
		assertTrue(true);
	}

}
