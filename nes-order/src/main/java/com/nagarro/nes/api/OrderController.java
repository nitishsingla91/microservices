package com.nagarro.nes.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.dtos.OrderDO;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.models.Order;
import com.nagarro.nes.services.IOrderService;
import com.nagarro.nes.validators.OrderInputValidator;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/orders")
@Api
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@Autowired
	private OrderInputValidator inputValidator;

	@GetMapping("{userID}")
	public ResponseEntity<BaseResponse<List<Order>>> getOrders(
			@RequestParam(name = "orderID", required = false) final String orderID,
			@PathVariable("userID") final String userID) throws ApplicationException {
		LOGGER.info("Received request to get order user id:{}", userID);
		return ResponseEntity.ok(new BaseResponse<>(this.orderService.getOrders(orderID, userID)));
	}

	@PostMapping
	public ResponseEntity<BaseResponse<Order>> createOrder(@RequestBody @Valid final OrderDO order,
			final BindingResult bindingResult) throws ApplicationException {
		LOGGER.info("Received request to create order:{}", order);
		inputValidator.checkBindingResults(bindingResult);
		return ResponseEntity.ok(new BaseResponse<>(this.orderService.createOrder(order)));
	}

}
