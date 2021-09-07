package com.nagarro.nes.models;

public class BaseErrorResponse<T> {

	/** The error code. */
	private String errorCode;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new base error response DO.
	 */
	public BaseErrorResponse() {
		super();
	}

	public BaseErrorResponse(String errorCode, String message) {
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
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BaseErrorResponse [errorCode=" + errorCode + ", message=" + message + "]";
	}

}
