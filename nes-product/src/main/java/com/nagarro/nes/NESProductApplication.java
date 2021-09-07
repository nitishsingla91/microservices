package com.nagarro.nes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NESProductApplication {

	public static void main(final String[] args) {
		SpringApplication.run(NESProductApplication.class, args);
	}

}
