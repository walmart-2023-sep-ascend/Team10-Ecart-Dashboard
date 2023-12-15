package com.wm.userdashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * r0m09yu - Raju Mummidi
 * s0k05sw - Sowmyalakshmi KL
 */

@ComponentScan("com.wm.userdashboard")
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Userdashboard API", version = "2.0", description = "UserDashboard Information for ECart"))
public class UserDashboardApplication {

	private static final Logger logger = LoggerFactory.getLogger(UserDashboardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserDashboardApplication.class, args);
		logger.info("Application started successfully..!");
	}

}
