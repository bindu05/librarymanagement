package com.capgemini.librarymanagement.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BooksTransaction implements Serializable {

	@Id
	@Column(name="transaction_id")
	private String transactionId;
	
	@Id
	@Column(name="registration_id")
	private String registrationId;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@Column
	private long fine;

	// Getters and Setters
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public long getFine() {
		return fine;
	}
	public void setFine(long l) {
		this.fine = l;
	}

}
