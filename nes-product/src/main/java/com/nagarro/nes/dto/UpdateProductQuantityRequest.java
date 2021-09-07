package com.nagarro.nes.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.nagarro.nes.enums.UpdateQuantityType;

/**
 * The Class UpdateProductQuantityRequest.
 *
 * @author chetanmahajan
 */
public class UpdateProductQuantityRequest {

	/** The quantity. */
	@Min(1)
	private long quantity;

	/** The update quantity type. */
	@NotNull
	private UpdateQuantityType updateQuantityType;

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the update quantity type.
	 *
	 * @return the update quantity type
	 */
	public UpdateQuantityType getUpdateQuantityType() {
		return updateQuantityType;
	}

	/**
	 * Sets the update quantity type.
	 *
	 * @param updateQuantityType the new update quantity type
	 */
	public void setUpdateQuantityType(final UpdateQuantityType updateQuantityType) {
		this.updateQuantityType = updateQuantityType;
	}

	@Override
	public String toString() {
		return "UpdateProductQuantityRequest [quantity=" + quantity + ", updateQuantityType=" + updateQuantityType
				+ "]";
	}

}
