package com.nagarro.nes.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nagarro.nes.enums.PaymentType;

public class PaymentRequestDO {

	@NotEmpty
	private String orderID;

	@NotNull
	private PaymentType paymentType;

	@NotNull
	private Long amount;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
