package com.nagarro.nes.exception;

import com.nagarro.nes.enums.ErrorCodeType;

/**
 * The Class ProductNotFoundException.
 *
 * @author chetanmahajan
 */
public class ProductNotFoundException extends ApplicationException {

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
	public ProductNotFoundException(final String message, final ErrorCodeType error) {
		super(message, error);
	}

}
