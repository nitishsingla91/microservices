package com.nagarro.nes.utils;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.nes.models.Delivery;
import com.nagarro.nes.store.DeliveryStore;

public class DeliveryUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryUtils.class);

	private DeliveryUtils() {
		super();
	}

	public static String generateUniqueDeliveryID() {
		String deliveryIDGenerated = null;
		do {
			deliveryIDGenerated = UUID.randomUUID().toString();
		} while (isPresentDeliveryID(deliveryIDGenerated));
		LOGGER.info("Unique Delivery Id generated:{}", deliveryIDGenerated);
		return deliveryIDGenerated;
	}

	private static boolean isPresentDeliveryID(final String deliveryIDGenerated) {
		final Optional<Delivery> optionalDelivery = DeliveryStore.DELIVERIES_AVAILABLE.stream()
				.filter(delivery -> delivery.getDeliveryID().equalsIgnoreCase(deliveryIDGenerated)).findAny();
		return optionalDelivery.isPresent();
	}

}
