package com.nagarro.nes.models;

import java.io.Serializable;

/**
 * The Class ResponseMessage.
 *
 * @author chetanmahajan
 */
public class ValidationResponseMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The field. */
	private String field;

	/** The code. */
	private String code;

	/**
	 * Instantiates a new response message.
	 */
	public ValidationResponseMessage() {
	}

	/**
	 * Instantiates a new response message.
	 *
	 * @param field the field
	 * @param code  the code
	 */
	public ValidationResponseMessage(String field, String code) {
		super();
		this.field = field;
		this.code = code;
	}

	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
