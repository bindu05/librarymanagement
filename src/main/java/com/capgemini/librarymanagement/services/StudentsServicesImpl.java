package com.capgemini.librarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.dao.StudentsDAO;

@Service
public class StudentsServicesImpl implements StudentsServices {
	
	@Autowired
	private StudentsDAO dao;

	@Override
	public BooksInventory requestBook(BooksInventory book) {
		return dao.requestBook(book);
	}

}
