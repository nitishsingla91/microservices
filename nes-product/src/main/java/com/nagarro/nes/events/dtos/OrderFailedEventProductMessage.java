package com.nagarro.nes.events.dtos;

import javax.validation.constraints.Min;

public class OrderFailedEventProductMessage {

	/** The quantity. */
	@Min(1)
	private long quantity;

	private String productID;

	public OrderFailedEventProductMessage() {
		super();
	}

	public OrderFailedEventProductMessage(@Min(1) long quantity, String productID) {
		super();
		this.quantity = quantity;
		this.productID = productID;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

}
