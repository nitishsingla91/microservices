package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public class OrderFailedException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public OrderFailedException(String message, ErrorCodeType error) {
		super(message, error);
	}

}
