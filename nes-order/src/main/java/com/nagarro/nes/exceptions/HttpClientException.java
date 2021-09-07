package com.nagarro.nes.exceptions;

import com.nagarro.nes.enums.ErrorCodeType;

public class HttpClientException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public HttpClientException(String message) {
		super(message);
	}

	public HttpClientException(ErrorCodeType error) {
		super(error);
	}

}
