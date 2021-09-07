package com.nagarro.nes.api;

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
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.dtos.PaymentRequestDO;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.models.Payment;
import com.nagarro.nes.services.IPaymentService;
import com.nagarro.nes.validators.PaymentInputValidator;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/payment")
@Api
public class PaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private PaymentInputValidator inputValidator;

	@PostMapping
	public ResponseEntity<BaseResponse<Payment>> makePayment(@RequestBody @Valid final PaymentRequestDO payment,
			final BindingResult requestBindingRequest) throws ApplicationException {
		LOGGER.info("Request received to add payment:{}", payment);
		inputValidator.checkBindingResults(requestBindingRequest);
		return ResponseEntity.ok(new BaseResponse<>(this.paymentService.makePayment(payment)));
	}

	@GetMapping("{orderID}")
	public ResponseEntity<BaseResponse<Payment>> getPaymentDetails(@PathVariable("orderID") final String orderID)
			throws ApplicationException {
		LOGGER.info("Request received to get payment details for orderID:{}", orderID);
		return ResponseEntity.ok(new BaseResponse<>(this.paymentService.getPayment(orderID)));
	}

}
