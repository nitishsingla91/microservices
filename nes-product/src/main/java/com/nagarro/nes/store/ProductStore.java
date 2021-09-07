package com.nagarro.nes.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nagarro.nes.models.Product;

/**
 * The Class ProductStore.
 *
 * @author chetanmahajan
 */
@Component
public class ProductStore {

	/** The Constant LOGGER. */
	private final static Logger LOGGER = LoggerFactory.getLogger(ProductStore.class);

	public final static List<Product> PRODUCTS_AVAILABLE = new ArrayList<>();

	@PostConstruct
	public void init() {
		LOGGER.debug("Storing some products before initialisation");
		final String PRODUCT_CATEOGORY = "Deodorant";
		PRODUCTS_AVAILABLE.add(new Product("111211", "Adidas Deodorant", "Adidas Dynamic Pulse deodorant body spray",
				220L, PRODUCT_CATEOGORY, 10));
		PRODUCTS_AVAILABLE.add(new Product("11212", "Man Company Deodorant", "Man Company Pulse deodorant body spray",
				320L, PRODUCT_CATEOGORY, 20));
		PRODUCTS_AVAILABLE.add(new Product("11313", "Signature Deodarant",
				"Signature Dynamic Pulse deodorant body spray", 120L, PRODUCT_CATEOGORY, 30));
		PRODUCTS_AVAILABLE.add(new Product("11414", "Fogg Deo", "Fogg Dynamic Pulse deodorant body spray", 200L,
				PRODUCT_CATEOGORY, 10));
		PRODUCTS_AVAILABLE.add(new Product("11515", "Bulgary Deo", "Bulgary Dynamic Pulse deodorant body spray", 4220L,
				PRODUCT_CATEOGORY, 10));
	}

}
