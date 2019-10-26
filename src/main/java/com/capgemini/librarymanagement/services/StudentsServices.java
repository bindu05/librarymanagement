package com.capgemini.librarymanagement.services;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;

public interface StudentsServices {
	
	public BooksRegistration requestBook(BooksInventory book, String userId);
	
}
