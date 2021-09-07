package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public class UserUpdateFailedException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public UserUpdateFailedException(String message, ErrorCodeType error) {
		super(message, error);
	}

}
