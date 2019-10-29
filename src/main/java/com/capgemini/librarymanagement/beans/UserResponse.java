package com.capgemini.librarymanagement.beans;

import java.io.Serializable;

public class UserResponse implements Serializable {
	
	private int statusCode;
	private String message;
	private String description;
	private Users user;
	private BooksInventory book;
	
	public BooksInventory getBook() {
		return book;
	}
	public void setBook(BooksInventory book) {
		this.book = book;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	

}
