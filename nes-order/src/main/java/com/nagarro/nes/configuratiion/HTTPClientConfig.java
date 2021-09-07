package com.nagarro.nes.configuratiion;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HTTPClientConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	// Override timeouts in request factory
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(3000);
		// Read timeout
		clientHttpRequestFactory.setReadTimeout(5000);
		return clientHttpRequestFactory;
	}

}
