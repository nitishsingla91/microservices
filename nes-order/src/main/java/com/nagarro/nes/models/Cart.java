package com.nagarro.nes.models;

import java.util.List;

public class Cart {

	private String userID;

	private List<CartDetails> cartDetails;

	public Cart(final String userID) {
		super();
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(final String userID) {
		this.userID = userID;
	}

	public List<CartDetails> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(final List<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "Cart [userID=" + userID + ", cartDetails=" + cartDetails + "]";
	}

}
