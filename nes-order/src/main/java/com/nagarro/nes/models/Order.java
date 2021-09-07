package com.nagarro.nes.models;

import java.time.LocalDateTime;
import java.util.List;

import com.nagarro.nes.dtos.ItemDetail;
import com.nagarro.nes.enums.OrderState;

public class Order {

	private String userId;

	private String orderID;

	private OrderState orderState = OrderState.CREATED;

	private List<ItemDetail> items;

	private String deliveryAddress;

	private Long amount;

	private LocalDateTime orderDate;

	private LocalDateTime delvieryDate;

	public Order(final String orderID, final String deliveryAddress, final String userID) {
		super();
		this.orderID = orderID;
		this.deliveryAddress = deliveryAddress;
		this.userId = userID;
	}

	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(final List<ItemDetail> items) {
		this.items = items;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	public String getOrderID() {
		return orderID;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDelvieryDate() {
		return delvieryDate;
	}

	public void setDelvieryDate(LocalDateTime delvieryDate) {
		this.delvieryDate = delvieryDate;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderID=" + orderID + ", orderState=" + orderState + ", items=" + items
				+ ", deliveryAddress=" + deliveryAddress + ", amount=" + amount + ", orderDate=" + orderDate
				+ ", delvieryDate=" + delvieryDate + "]";
	}

}
