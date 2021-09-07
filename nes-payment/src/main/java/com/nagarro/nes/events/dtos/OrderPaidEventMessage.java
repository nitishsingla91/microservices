package com.nagarro.nes.events.dtos;

public class OrderPaidEventMessage {

	private String paymentId;

	private String orderID;

	private long amount;

	private PaymentStatus paymentStatus;

	public OrderPaidEventMessage() {
		super();
	}

	public OrderPaidEventMessage(String paymentId, String orderID, long amount, PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.orderID = orderID;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "OrderPaidEventMessage [paymentId=" + paymentId + ", orderID=" + orderID + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + "]";
	}

}
