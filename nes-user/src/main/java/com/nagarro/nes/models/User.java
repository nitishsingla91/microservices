package com.nagarro.nes.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nagarro.nes.enums.UserType;

/**
 * The user type.
 *
 * @author chetanmahajan
 */
public class User {

	/** The user id. */
	@NotEmpty
	private String userId;

	/** The user name. */
	@NotEmpty
	private String userFirstName;

	/** The user last name. */
	@NotEmpty
	private String userLastName;

	/** The password. */
	@NotEmpty
	private String password;

	/** The user type. */
	@NotNull
	private UserType userType;

	/** The address. */
	@NotEmpty
	private String address;

	/** The email id. */
	@NotEmpty
	private String emailId;

	/** The contact number. */
	@NotNull
	private Long contactNumber;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userId        the user id
	 * @param userFirstName the user first name
	 * @param userLastName  the user last name
	 * @param password      the password
	 * @param userType      the user type
	 * @param address       the address
	 */
	public User(final String userId, final String userFirstName, final String userLastName, final String password,
			final UserType userType, final String address, final String emailId, final Long contactNumber) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.password = password;
		this.userType = userType;
		this.address = address;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(final UserType userType) {
		this.userType = userType;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Gets the user first name.
	 *
	 * @return the user first name
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * Sets the user first name.
	 *
	 * @param userFirstName the new user first name
	 */
	public void setUserFirstName(final String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * Gets the user last name.
	 *
	 * @return the user last name
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * Sets the user last name.
	 *
	 * @param userLastName the new user last name
	 */
	public void setUserLastName(final String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(final Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", password=" + password + ", userType=" + userType + ", address=" + address + ", emailId=" + emailId
				+ ", contactNumber=" + contactNumber + "]";
	}

}
