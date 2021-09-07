package com.nagarro.nes.models;

import com.nagarro.nes.enums.DeliveryState;

public class Delivery {

	private String orderID;

	private DeliveryState deliveryStatus;

	private String deliveryID;

	public Delivery() {
		super();
	}

	public Delivery(String orderID, DeliveryState deliveryStatus, String deliveryID) {
		super();
		this.orderID = orderID;
		this.deliveryStatus = deliveryStatus;
		this.deliveryID = deliveryID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(final String orderID) {
		this.orderID = orderID;
	}

	public DeliveryState getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(final DeliveryState deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryID() {
		return deliveryID;
	}

	public void setDeliveryID(final String deliveryID) {
		this.deliveryID = deliveryID;
	}

	@Override
	public String toString() {
		return "Delivery [orderID=" + orderID + ", deliveryStatus=" + deliveryStatus + ", deliveryID=" + deliveryID
				+ "]";
	}

}
