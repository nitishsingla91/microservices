package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public abstract class ApplicationException extends Exception {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** cause of original java exception. */
	private Throwable cause;

	/** Application exception code. */
	private final ErrorCodeType error;

	/**
	 * error message
	 *
	 */
	private String message;

	/**
	 * Instantiates a new base exception.
	 *
	 * @param error the error type
	 */
	public ApplicationException(final ErrorCodeType error) {
		super(error.getMessage());
		this.message = error.getMessage();
		this.error = error;
	}

	/**
	 * Instantiates a new wallet exception.
	 *
	 * @param message the message
	 */
	public ApplicationException(final String message) {
		super(message);
		this.message = message;
		this.error = null;
	}

	public ApplicationException(final String message, final ErrorCodeType error) {
		super(message);
		this.message = message;
		this.error = error;
	}

	public ApplicationException(final String message, final ErrorCodeType error, Throwable cause) {
		super(message);
		this.message = message;
		this.error = error;
		this.cause = cause;
	}

	@Override
	public synchronized Throwable getCause() {
		return this.cause;
	}

	/**
	 * Gets the error type.
	 *
	 * @return the error type
	 */
	public ErrorCodeType getError() {
		return error;
	}

	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the cause.
	 *
	 * @param cause the new cause
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
