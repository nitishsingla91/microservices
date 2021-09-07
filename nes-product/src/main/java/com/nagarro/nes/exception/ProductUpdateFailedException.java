package com.nagarro.nes.exception;

import com.nagarro.nes.enums.ErrorCodeType;

public class ProductUpdateFailedException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ProductUpdateFailedException(String message, ErrorCodeType error) {
		super(message, error);
	}

}
