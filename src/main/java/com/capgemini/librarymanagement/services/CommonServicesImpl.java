package com.capgemini.librarymanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.dao.CommonDAO;

@Service
public class CommonServicesImpl implements CommonServices {
	
	@Autowired
	private CommonDAO dao;

	@Override
	public Users login(String userId, String password) {
		return dao.login(userId, password);
	}

	@Override
	public List<Users> showAllStudentsInfo() {
		return dao.showAllStudentsInfo();
	}

	@Override
	public List<Users> showAllLibrariansInfo() {
		return dao.showAllLibrariansInfo();
	}

	@Override
	public List<BooksInventory> searchBookByName(String name) {
		return dao.searchBookByName(name);
	}

	@Override
	public List<BooksInventory> searchBookByAuthor(String author1) {
		return dao.searchBookByAuthor(author1);
	}

	

	

}
