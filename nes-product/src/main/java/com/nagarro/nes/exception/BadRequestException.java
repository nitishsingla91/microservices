package com.nagarro.nes.exception;

import java.util.List;

import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.models.ValidationResponseMessage;

/**
 * The Class BadRequestException.
 *
 * @author chetanmahajan
 */
public class BadRequestException extends ApplicationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The response messages. */
	List<ValidationResponseMessage> responseMessages;

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param message the message
	 * @param error   the error
	 */
	public BadRequestException(String message, ErrorCodeType error) {
		super(message, error);
	}

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param error            the error
	 * @param responseMessages the response messages
	 */
	public BadRequestException(ErrorCodeType error, List<ValidationResponseMessage> responseMessages) {
		super(error);
		this.responseMessages = responseMessages;
	}

	/**
	 * Gets the response messages.
	 *
	 * @return the response messages
	 */
	public List<ValidationResponseMessage> getResponseMessages() {
		return responseMessages;
	}

	/**
	 * Sets the response messages.
	 *
	 * @param responseMessages the new response messages
	 */
	public void setResponseMessages(List<ValidationResponseMessage> responseMessages) {
		this.responseMessages = responseMessages;
	}

}
