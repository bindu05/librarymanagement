package com.capgemini.librarymanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksTransaction;
import com.capgemini.librarymanagement.dao.LibrarianDAO;

@Service
public class LibrarianServicesImpl implements LibrarianServices {
	
	@Autowired
	private LibrarianDAO dao;

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
		return dao.deleteBook(bookId);
	}

	@Override
	public List<BooksTransaction> showAllIssuedBooksInfo() {
		return dao.showAllIssuedBooksInfo();
	}

	@Override
	public List<BooksInventory> showAllRequestedBooksInfo() {
		return dao.showAllRequestedBooksInfo();
	}

}
