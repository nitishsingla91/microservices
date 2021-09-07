package com.nagarro.nes.utils;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.nes.models.Payment;
import com.nagarro.nes.store.PaymentStore;

public class PaymentUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentUtils.class);

	private PaymentUtils() {
		super();
	}

	/**
	 * Generate unique payment ID.
	 *
	 * @return the string
	 */
	public static String generateUniquePaymentID() {
		String paymentIdGenerated = null;
		do {
			paymentIdGenerated = UUID.randomUUID().toString();
		} while (isPresent(paymentIdGenerated));
		LOGGER.info("Unique Payment Id generated:{}", paymentIdGenerated);
		return paymentIdGenerated;
	}

	/**
	 * Checks if is unique.
	 *
	 * @param paymentIdGenerated the payment id generated
	 * @return true, if is unique
	 */
	private static boolean isPresent(final String paymentIdGenerated) {
		final Optional<Payment> optionalPayment = PaymentStore.PAYMENTS_AVAILABLE.stream()
				.filter(payment -> payment.getPaymentId().toLowerCase().equalsIgnoreCase(paymentIdGenerated)).findAny();
		return optionalPayment.isPresent();
	}

}
