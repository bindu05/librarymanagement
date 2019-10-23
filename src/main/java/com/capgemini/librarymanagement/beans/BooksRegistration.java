package com.capgemini.librarymanagement.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BooksRegistration {
	
	@Id
	@Column(name="registration_id")
	private String registrationId;
	
	@Id
	@Column(name="book_id")
	private String bookId;
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="registrationdate")
	private Date registrationDate;
	
	// Getters and Setters
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	

}
