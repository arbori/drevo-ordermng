package com.ordermng.core.dto;

public class UserDTO {
	protected String name;
	protected String fullName;
	protected String email;
	protected Boolean emailConfirmed;
	protected Boolean active;

	/**
	 * Create a new user object seting all attributes.
	 * @param name
	 * @param fullName
	 * @param email
	 * @param emailConfirmed
	 * @param active
	 */
	public UserDTO(String name, String fullName, String email, Boolean emailConfirmed, Boolean active) {
		this.name = name;
		this.fullName = fullName;
		this.email = email;
		this.emailConfirmed = emailConfirmed;
		this.active = active;
	}

	/**
	 * Makes a copy of gave user.
	 * @param user
	 */
	public UserDTO(UserDTO user) {
		this.name = user.name;
		this.fullName = user.fullName;
		this.email = user.email;
		this.emailConfirmed = user.emailConfirmed;
		this.active = user.active;
	}

	// Read-only property name
	public String getName() {
		return name;
	}

	// Property full name
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	// Property email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// Read-only property email confirmed
	public Boolean getEmailConfirmed() {
		return emailConfirmed;
	}

	// Read-only property active
	public Boolean getActive() {
		return active;
	}
}
