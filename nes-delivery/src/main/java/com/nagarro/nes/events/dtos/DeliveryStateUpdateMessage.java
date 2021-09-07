package com.nagarro.nes.events.dtos;

import com.nagarro.nes.enums.DeliveryState;

public class DeliveryStateUpdateMessage {

	private String orderID;

	private DeliveryState deliveryState;

	public DeliveryStateUpdateMessage() {
		super();
	}

	public DeliveryStateUpdateMessage(String orderID, DeliveryState deliveryState) {
		super();
		this.orderID = orderID;
		this.deliveryState = deliveryState;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public DeliveryState getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(DeliveryState deliveryState) {
		this.deliveryState = deliveryState;
	}

}
