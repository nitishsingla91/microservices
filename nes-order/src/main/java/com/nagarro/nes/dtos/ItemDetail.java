package com.nagarro.nes.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemDetail {

	@NotEmpty
	private String productID;

	@NotNull
	@Min(1)
	private Long quantity;

	@NotNull
	@Min(1)
	private long price;

	public String getProductID() {
		return productID;
	}

	public void setProductID(final String productID) {
		this.productID = productID;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(final Long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(final long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemDetail [productID=" + productID + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
