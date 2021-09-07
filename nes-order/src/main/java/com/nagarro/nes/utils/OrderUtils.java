package com.nagarro.nes.utils;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.nes.models.Order;
import com.nagarro.nes.store.OrderStore;

public class OrderUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderUtils.class);

	private OrderUtils() {
		super();
	}

	/**
	 * Generate unique payment ID.
	 *
	 * @return the string
	 */
	public static String generateUniqueOrderID() {
		String orderIDGenerated = null;
		do {
			orderIDGenerated = UUID.randomUUID().toString();
		} while (isPresentOrderID(orderIDGenerated));
		LOGGER.info("Unique Payment Id generated:{}", orderIDGenerated);
		return orderIDGenerated;
	}

	/**
	 * Checks if is unique.
	 *
	 * @param paymentIdGenerated the payment id generated
	 * @return true, if is unique
	 */
	private static boolean isPresentOrderID(final String orderIDGenerated) {
		final Optional<Order> optionalOrder = OrderStore.ORDERS_AVAILABLE.stream()
				.filter(order -> order.getOrderID().equalsIgnoreCase(orderIDGenerated)).findAny();
		return optionalOrder.isPresent();
	}

}
