package com.capgemini.librarymanagement.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.BooksTransaction;
import com.capgemini.librarymanagement.dao.LibrarianDAO;
import com.capgemini.librarymanagement.utility.Validation;

@Service
public class LibrarianServicesImpl implements LibrarianServices {
	
	@Autowired
	private LibrarianDAO dao;
	
	@Autowired
	private Validation validation;

	@Override
	public Boolean addBook(BooksInventory book) {
		return dao.addBook(book);
	}

	@Override
	public Boolean updateBook(BooksInventory book) {
		return dao.updateBook(book);
	}

	@Override
	public Boolean deleteBook(String bookId) {
		validation.regexId(bookId);
		return dao.deleteBook(bookId);
	}

	@Override
	public List<BooksTransaction> showAllIssuedBooksInfo() {
		return dao.showAllIssuedBooksInfo();
	}

	@Override
	public List<BooksRegistration> showAllRequestedBooksInfo() {
		return dao.showAllRequestedBooksInfo();
	}

	@Override
	public BooksTransaction acceptRequest(String registrationId) {
		validation.regexId(registrationId);
		return dao.acceptRequest(registrationId);
	}

	@Override
	public List<BooksInventory> showAllBooks() {
		return dao.showAllBooks();
	}

	@Override
	public BooksTransaction addFine(String registrationId, Date returnDate) {
		validation.regexId(registrationId);
		return dao.addFine(registrationId, returnDate);
	}

}
