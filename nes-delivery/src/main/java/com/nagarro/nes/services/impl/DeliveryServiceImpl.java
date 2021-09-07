package com.nagarro.nes.services.impl;

import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nes.enums.DeliveryState;
import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.events.dtos.DeliveryStateUpdateMessage;
import com.nagarro.nes.events.producer.DeliveryStateUpdatedProducer;
import com.nagarro.nes.exceptions.DeliveryNotFoundException;
import com.nagarro.nes.models.Delivery;
import com.nagarro.nes.services.IDeliveryService;
import com.nagarro.nes.store.DeliveryStore;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Autowired
	private DeliveryStateUpdatedProducer deliveryStateProducer;

	@Override
	public Delivery updateDeliveryState(final String orderID, final DeliveryState deliveryState)
			throws DeliveryNotFoundException {
		LOGGER.info("Updating state of order with id:{} with state:{}", orderID, deliveryState);
		final Predicate<Delivery> filterPredicate = delivery -> delivery.getOrderID().toLowerCase()
				.equalsIgnoreCase(orderID.toLowerCase());
		final Optional<Delivery> optionalDelivery = this.getDeliveryByPredicate(filterPredicate);
		final Delivery retDelivery = optionalDelivery
				.orElseThrow(() -> new DeliveryNotFoundException(ErrorCodeType.DELIVERY_NOT_FOUND.getMessage(),
						ErrorCodeType.DELIVERY_NOT_FOUND));
		retDelivery.setDeliveryStatus(deliveryState);
		this.deliveryStateProducer.produceEventProductUpdate(new DeliveryStateUpdateMessage(orderID, deliveryState));
		return retDelivery;
	}

	@Override
	public Delivery getDeliveryDetailsByDeliveryID(final String deliveryID) throws DeliveryNotFoundException {
		LOGGER.info("Getting delivery with deliveryid:{}", deliveryID);
		final Predicate<Delivery> filterPredicate = delivery -> delivery.getDeliveryID().toLowerCase()
				.equalsIgnoreCase(deliveryID.toLowerCase());
		final Optional<Delivery> optionalDelivery = this.getDeliveryByPredicate(filterPredicate);
		return optionalDelivery
				.orElseThrow(() -> new DeliveryNotFoundException(ErrorCodeType.DELIVERY_NOT_FOUND.getMessage(),
						ErrorCodeType.DELIVERY_NOT_FOUND));
	}

	@Override
	public Delivery getDeliveryDetailsByOrderID(final String orderID) throws DeliveryNotFoundException {
		LOGGER.info("Getting delivery with order:{}", orderID);
		final Predicate<Delivery> filterPredicate = delivery -> delivery.getOrderID().toLowerCase()
				.equalsIgnoreCase(orderID.toLowerCase());
		final Optional<Delivery> optionalDelivery = this.getDeliveryByPredicate(filterPredicate);
		return optionalDelivery
				.orElseThrow(() -> new DeliveryNotFoundException(ErrorCodeType.DELIVERY_NOT_FOUND.getMessage(),
						ErrorCodeType.DELIVERY_NOT_FOUND));
	}

	private Optional<Delivery> getDeliveryByPredicate(final Predicate<Delivery> predicate) {
		return DeliveryStore.DELIVERIES_AVAILABLE.stream().filter(predicate).findFirst();
	}

}
