package com.nagarro.nes.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodeType {

	/** The entity not found. */
	ORDER_NOT_FOUND("notfound.order", HttpStatus.OK, "Order for given id is not present"),
	INVALID_REQUEST("invalid.request", HttpStatus.BAD_REQUEST, "Invalid Request for requested API"),
	ORDER_PLACED_FAILED("failed.place.order", HttpStatus.INTERNAL_SERVER_ERROR),
	CART_PRODUCT_NOT_FOUND("notfound.cart.product", HttpStatus.OK, "Product in cart is not present"),
	CART_NOT_FOUND("notfound.cart", HttpStatus.OK, "Cart for given request is not found"),
	USER_ORDER_NOT_FOUND("notfound.user.order", HttpStatus.OK, "Order for given user is not present"),
	FAILED_CONNECTION_PRODUCT_SERVICE("failed.connection.product", "Failed to get response from product"),
	CART_UPDATE_FAILED("failed.update.cart", HttpStatus.OK, "Failed to update cart");

	/**
	 * The error code.
	 */
	private String errorCode;

	/**
	 * The http status code.
	 */
	private HttpStatus httpStatusCode;

	/**
	 * The error description Message.
	 */
	private String message;

	/**
	 * Instantiates a new wallet application exception code.
	 *
	 * @param errorCode      the error code
	 * @param httpStatusCode the http status code
	 * @param message        the message
	 */
	private ErrorCodeType(final String errorCode, final HttpStatus httpStatusCode, final String message) {
		this.errorCode = errorCode;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	/**
	 * Instantiates a new error code type.
	 *
	 * @param errorCode      the error code
	 * @param httpStatusCode the http status code
	 */
	private ErrorCodeType(String errorCode, HttpStatus httpStatusCode) {
		this.errorCode = errorCode;
		this.httpStatusCode = httpStatusCode;
	}

	private ErrorCodeType(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the http status code.
	 *
	 * @return the http status code
	 */
	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
