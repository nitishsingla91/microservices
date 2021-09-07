package com.nagarro.nes.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nes.dtos.PaymentRequestDO;
import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.events.dtos.OrderPaidEventMessage;
import com.nagarro.nes.events.dtos.PaymentStatus;
import com.nagarro.nes.events.producer.OrderPaidEventProducer;
import com.nagarro.nes.exceptions.PaymentFailedException;
import com.nagarro.nes.exceptions.PaymentNotFoundException;
import com.nagarro.nes.models.Payment;
import com.nagarro.nes.services.IPaymentService;
import com.nagarro.nes.store.PaymentStore;
import com.nagarro.nes.utils.PaymentUtils;

@Service
public class PaymentServiceImpl implements IPaymentService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private OrderPaidEventProducer orderPaidEventProducer;

	@Override
	public Payment makePayment(final PaymentRequestDO request) throws PaymentFailedException {
		OrderPaidEventMessage orderPaidEventMessage = null;
		Payment payment = new Payment(request.getOrderID(), request.getPaymentType(), request.getAmount());
		payment.setPaidOn(LocalDateTime.now());
		LOGGER.info("Make payment for order id:{}", payment.getOrderID());
		payment.setPaymentId(PaymentUtils.generateUniquePaymentID());
		if (request.getOrderID().equalsIgnoreCase("abcde")) {
			LOGGER.debug("Payment Failed");
			payment.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
			orderPaidEventMessage = new OrderPaidEventMessage(payment.getPaymentId(), payment.getOrderID(),
					payment.getAmount(), PaymentStatus.PAYMENT_FAILED);
		} else {
			LOGGER.debug("Payment Success");
			payment.setPaymentStatus(PaymentStatus.PAID);
			orderPaidEventMessage = new OrderPaidEventMessage(payment.getPaymentId(), payment.getOrderID(),
					payment.getAmount(), PaymentStatus.PAID);
		}

		PaymentStore.PAYMENTS_AVAILABLE.add(payment);
		this.orderPaidEventProducer.produceEventProductUpdate(orderPaidEventMessage);
		return payment;

	}

	@Override
	public Payment getPayment(final String orderID) throws PaymentNotFoundException {
		LOGGER.info("Getting payment details of orderID:{}", orderID);
		final Optional<Payment> optionalRequiredPayment = PaymentStore.PAYMENTS_AVAILABLE.stream()
				.filter(payment -> payment.getOrderID().toLowerCase().equalsIgnoreCase(orderID.toLowerCase()))
				.findFirst();
		return optionalRequiredPayment
				.orElseThrow(() -> new PaymentNotFoundException("Payment with requested Order ID not found",
						ErrorCodeType.PAYMENT_NOT_FOUND));
	}

}
