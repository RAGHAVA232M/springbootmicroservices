package com.springboot.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDiscoveryServiceApplication.class, args);
	}

}
