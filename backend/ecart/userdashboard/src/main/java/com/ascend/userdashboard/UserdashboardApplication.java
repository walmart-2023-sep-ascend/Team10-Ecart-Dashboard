package com.ascend.userdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories
public class UserdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdashboardApplication.class, args);
	}

	
}
