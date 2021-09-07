package com.nagarro.nes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NESDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NESDiscoveryServerApplication.class, args);
	}

}
