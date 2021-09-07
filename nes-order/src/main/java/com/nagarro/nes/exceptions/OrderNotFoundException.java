package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public class OrderNotFoundException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(final String message, final ErrorCodeType error) {
		super(message, error);
	}

}
