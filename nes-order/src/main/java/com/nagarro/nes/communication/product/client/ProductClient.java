package com.nagarro.nes.communication.product.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nagarro.nes.communication.product.dtos.Product;
import com.nagarro.nes.communication.product.dtos.UpdateProductQuantityRequest;
import com.nagarro.nes.communication.product.dtos.UpdateQuantityType;
import com.nagarro.nes.exceptions.HttpClientException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.services.impl.OrderServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class ProductClient {

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@HystrixCommand(fallbackMethod = "fallbackCheckAndRemoveItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public void checkAndRemoveItem(final String productID, final long quantity) throws HttpClientException {
		final UpdateProductQuantityRequest request = new UpdateProductQuantityRequest(quantity, UpdateQuantityType.DEC);
		ResponseEntity<BaseResponse<Product>> product = this.restTemplate.exchange(
				"http://nes-product/products/" + productID + "/quantity", HttpMethod.PUT, new HttpEntity<>(request),
				new ParameterizedTypeReference<BaseResponse<Product>>() {
				});
		if (product.getStatusCode() == HttpStatus.OK && product.getBody().getData() != null) {
			LOGGER.info("Successfully remove item from product :{} with id:{}", product.getBody().getData(), productID);
		} else {

			LOGGER.debug("Failed to update product:{} quantity of product id:{}", product.getBody().getErrorCode(),
					productID);
			throw new HttpClientException(product.getBody().getMessage());
		}
	}

	public void fallbackCheckAndRemoveItem(final String productID, final long quantity, Throwable e)
			throws HttpClientException {
		LOGGER.error("Hysterix called:{} due to :{} ", productID, e.getMessage());
		throw new HttpClientException(e.getMessage());
	}

}
