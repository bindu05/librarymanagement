package com.capgemini.librarymanagement.services;

import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.BooksTransaction;

public interface LibrarianServices {
	
	public Boolean addBook(BooksInventory book);
	public Boolean updateBook(BooksInventory book);
	public Boolean deleteBook(String bookId);
	public List<BooksInventory> showAllBooks();
	public BooksTransaction acceptRequest(String registrationId);
	public List<BooksTransaction> showAllIssuedBooksInfo();
	public List<BooksRegistration> showAllRequestedBooksInfo();
	public BooksTransaction addFine(String registrationId, Date returnDate);

}
