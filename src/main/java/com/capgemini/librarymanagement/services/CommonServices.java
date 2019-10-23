package com.capgemini.librarymanagement.services;

import java.util.List;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.Users;

public interface CommonServices {
	
	public Users login(Integer userId, String password);
	
	public List<Users> showAllStudentsInfo();
	public List<Users> showAllLibrariansInfo();
	public List<BooksInventory> searchBook(String name);

}
