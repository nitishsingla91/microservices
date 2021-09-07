package com.nagarro.nes.models;

import java.time.LocalDateTime;

import com.nagarro.nes.enums.PaymentType;
import com.nagarro.nes.events.dtos.PaymentStatus;

public class Payment {

	private String paymentId;

	private String orderID;

	private PaymentType paymentType;

	private PaymentStatus paymentStatus;

	private LocalDateTime paidOn;

	private Long amount;

	public Payment() {
		super();
	}

	public Payment(String orderID, PaymentType paymentType, Long amount) {
		super();
		this.orderID = orderID;
		this.paymentType = paymentType;
		this.amount = amount;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(final String orderID) {
		this.orderID = orderID;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(final PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaidOn() {
		return paidOn;
	}

	public void setPaidOn(LocalDateTime paidOn) {
		this.paidOn = paidOn;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}
