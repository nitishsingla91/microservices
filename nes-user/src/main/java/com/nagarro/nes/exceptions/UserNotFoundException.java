package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

/**
 * The Class ProductNotFoundException.
 *
 * @author chetanmahajan
 */
public class UserNotFoundException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new product not found exception.
	 *
	 * @param message the message
	 * @param error   the error
	 */
	public UserNotFoundException(final String message, final ErrorCodeType error) {
		super(message, error);
	}

}
