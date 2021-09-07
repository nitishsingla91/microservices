package com.nagarro.nes.enums;

import org.springframework.http.HttpStatus;

import com.nagarro.nes.exceptions.advices.ExceptionController;

/**
 * The Enum ErrorCodeType will contains all errors of this service with proper
 * message,code and HTTP status handled dynamically by the
 * {@link ExceptionController}.
 */
public enum ErrorCodeType {

	/** The entity not found. */
	USER_NOT_FOUND("notfound.user", HttpStatus.OK, "Record Not found"),
	INVALID_REQUEST("invalid.request", HttpStatus.BAD_REQUEST, "Invalid Request for requested API"),
	USER_UPDATE_FAILED("failed.update.user", HttpStatus.OK, "User updation failed");

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
