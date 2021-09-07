package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public abstract class SystemException extends RuntimeException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** cause of original java exception. */
	private Throwable cause;

	/** error enum . */
	private final ErrorCodeType error;

	/** The message. */
	private String message;

	public SystemException(ErrorCodeType error) {
		this.message = error.getMessage();
		this.error = error;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public Throwable getCause() {
		return cause;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ErrorCodeType getError() {
		return error;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#getMessage()
	 */
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
