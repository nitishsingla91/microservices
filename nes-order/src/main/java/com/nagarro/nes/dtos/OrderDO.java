package com.nagarro.nes.dtos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

public class OrderDO {

	@NotEmpty
	private String userID;

	@NotEmpty
	private String deliveryAddress;

	@NonNull
	@Valid
	private List<ItemDetail> items;

	public String getUserID() {
		return userID;
	}

	public void setUserID(final String userID) {
		this.userID = userID;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(final List<ItemDetail> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderDO [userID=" + userID + ", deliveryAddress=" + deliveryAddress + ", items=" + items + "]";
	}

}
