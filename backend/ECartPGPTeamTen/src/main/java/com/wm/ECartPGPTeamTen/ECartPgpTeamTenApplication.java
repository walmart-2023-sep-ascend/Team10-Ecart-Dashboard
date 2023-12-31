package com.wm.ECartPGPTeamTen;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * r0m09yu - Raju Mummidi
 */

@ComponentScan("com.wm.ECartPGPTeamTen")
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "ECart API", version = "2.0", description = "ECart Information"))
public class ECartPgpTeamTenApplication {

	private static final Logger logger = LoggerFactory.getLogger(ECartPgpTeamTenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ECartPgpTeamTenApplication.class, args);
		logger.info("Application started successfully..!");
	}

}
