package com.nagarro.nes.models;

public class CartDetails {

	private String productID;

	private long quantity = 1;

	public CartDetails(final String productID) {
		super();
		this.productID = productID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(final String productID) {
		this.productID = productID;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDetails [productID=" + productID + ", quantity=" + quantity + "]";
	}

}
