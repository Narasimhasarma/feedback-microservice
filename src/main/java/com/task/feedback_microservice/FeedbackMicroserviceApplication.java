package com.task.feedback_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FeedbackMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackMicroserviceApplication.class, args);
	}

}
