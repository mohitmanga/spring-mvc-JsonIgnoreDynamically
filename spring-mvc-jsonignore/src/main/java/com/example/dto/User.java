package com.example.dto;

import com.example.annotation.IgnoreFields;
import com.example.annotation.SourceFields;
import com.example.common.Platform;

/**
 * Provides information about an User
 *
 */
@IgnoreFields(sourceFields = {
		@SourceFields(fields = { "firstname" }, platform = Platform.ANDROID),
		@SourceFields(fields = { "postalCode", "address" }, platform = Platform.IOS) })
public class User {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private String postalCode;
	private String city;
	private String country;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}