package com.nagarro.nes.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodeType {

	/** The entity not found. */
	PAYMENT_NOT_FOUND("notfound.product", HttpStatus.OK, "Entity Not found"),
	INVALID_REQUEST("invalid.request", HttpStatus.BAD_REQUEST, "Invalid Request for requested API"),
	PAYMENT_FAILED("failed.payment", HttpStatus.INTERNAL_SERVER_ERROR, "Payment Failed due to some reason");

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
	 * @param errorCode the error code
	 * @param message   the message
	 */
	private ErrorCodeType(final String errorCode, final String message) {
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
