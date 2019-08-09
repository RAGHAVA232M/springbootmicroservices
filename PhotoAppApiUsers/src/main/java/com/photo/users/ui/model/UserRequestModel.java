package com.photo.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestModel {
	@NotNull(message="cant be null")
	@Size(min=2,message="size must be > 2")
	private String firstName;
	@NotNull(message="cant be null")
	private String lastName;
	@NotNull(message="cant be null")
	@Size(min=2,max=8,message="size must be > 2 and < 8")
	private String password;
	@Email
	private String email;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
