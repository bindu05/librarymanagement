package com.capgemini.librarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.dao.StudentsDAO;
import com.capgemini.librarymanagement.utility.Validation;
import com.capgemini.librarymanagement.utility.ValidationImpl;

@Service
public class StudentsServicesImpl implements StudentsServices {
	
	@Autowired
	private StudentsDAO dao;
	
	
	private Validation validation = new ValidationImpl();

	@Override
	public BooksRegistration requestBook(BooksInventory book, String userId) {
		
		return dao.requestBook(book, userId);
	}

}
