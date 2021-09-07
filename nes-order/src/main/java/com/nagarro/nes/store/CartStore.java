package com.nagarro.nes.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.nes.models.Cart;

public class CartStore {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderStore.class);

	public static final List<Cart> CARTS_AVAILABLE = new ArrayList<>();

	@PostConstruct
	public void init() {
		LOGGER.debug("Storing some users before initialisation");
	}

}
