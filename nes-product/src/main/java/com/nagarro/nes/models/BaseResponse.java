package com.nagarro.nes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class BaseResponse. will be the base response class for every api
 * response in case of success
 *
 * @author chetanmahajan
 * @param <T> the generic type
 */
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> extends BaseErrorResponse<T> {

	/** The data. */
	private T data;

	public BaseResponse() {
		super();
	}

	public BaseResponse(T data) {
		super();
		this.data = data;
	}

	/**
	 * Instantiates a new base response DO.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 */
	public BaseResponse(final String errorCode, final String message) {
		super(errorCode, message);
	}

	/**
	 * Instantiates a new base response DO.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 */
	public BaseResponse(T data, final String errorCode, final String message) {
		super(errorCode, message);
		this.data = data;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

}
