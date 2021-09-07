package com.nagarro.nes.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nagarro.nes.enums.UpdateCartProductType;

public class UpdateCartRequest {

	/** The quantity. */
	@NotEmpty
	private String productID;

	/** The update quantity type. */
	@NotNull
	private UpdateCartProductType updateCartType;

	public String getProductID() {
		return productID;
	}

	public void setProductID(final String productID) {
		this.productID = productID;
	}

	public UpdateCartProductType getUpdateCartType() {
		return updateCartType;
	}

	public void setUpdateCartType(final UpdateCartProductType updateCartType) {
		this.updateCartType = updateCartType;
	}

	@Override
	public String toString() {
		return "UpdateCartRequest [productID=" + productID + ", updateCartType=" + updateCartType + "]";
	}

}
