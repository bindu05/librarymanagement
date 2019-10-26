package com.capgemini.librarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.dao.StudentsDAO;
import com.capgemini.librarymanagement.utility.Validation;

@Service
public class StudentsServicesImpl implements StudentsServices {
	
	@Autowired
	private StudentsDAO dao;
	
	@Autowired
	private Validation validation;

	@Override
	public BooksRegistration requestBook(BooksInventory book, String userId) {
		validation.regexId(userId);
		return dao.requestBook(book, userId);
	}

}
