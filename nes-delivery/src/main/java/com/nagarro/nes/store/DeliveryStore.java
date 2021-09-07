package com.nagarro.nes.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nagarro.nes.models.Delivery;

@Component
public class DeliveryStore {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStore.class);

	public static final List<Delivery> DELIVERIES_AVAILABLE = new ArrayList<>();

	@PostConstruct
	public void init() {
		LOGGER.debug("Storing some users before initialisation");
	}

}
