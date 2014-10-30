package com.snn.library.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
	@Id
	private String id;

	private String name;
	private String author;
	private Integer quantity;
	private Boolean popular;
	private String captchaResponse;
	private String captchaChallenge;

	public Book() {
	}

	public Book(String id, String name, String author, Integer quantity, Boolean pupular) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.quantity = quantity;
		this.popular = pupular;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getPopular() {
		return popular;
	}

	public void setPopular(Boolean popular) {
		this.popular = popular;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	public String getCaptchaChallenge() {
		return captchaChallenge;
	}

	public void setCaptchaChallenge(String captchaChallenge) {
		this.captchaChallenge = captchaChallenge;
	}

}
