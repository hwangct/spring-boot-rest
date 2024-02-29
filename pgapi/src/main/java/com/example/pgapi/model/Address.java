package com.example.pgapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses", schema = "demo")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer number;
	
	@Column(name="street_address")
	private String street;
	private String postcode;
	
	public Address() {};
	
	public Address(Integer number, String street, String postcode) {
		this.number = number;
		this.street = street;
		this.postcode = postcode;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public String getStreet() {
		return street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
