package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;

public interface StudentsDAO {
	
	public BooksRegistration requestBook(BooksInventory book, String userId);

}
