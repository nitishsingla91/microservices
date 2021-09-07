package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public class CartUpdateFailedException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CartUpdateFailedException(ErrorCodeType error) {
		super(error);
	}

	public CartUpdateFailedException(String message, ErrorCodeType error) {
		super(message, error);
	}

}
