package com.capgemini.librarymanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.dao.CommonDAO;
import com.capgemini.librarymanagement.utility.Validation;
import com.capgemini.librarymanagement.utility.ValidationImpl;

@Service
public class CommonServicesImpl implements CommonServices {
	
	@Autowired
	private CommonDAO dao;
	

	private Validation validation = new ValidationImpl();

	@Override
	public Users login(String userId, String password) {
		validation.regexId(userId);
		validation.regexPassword(password);
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
	public List<BooksInventory> searchBookByAuthor(String author1) {
		return dao.searchBookByAuthor(author1);
	}

	@Override
	public Boolean cancelRequest(String registrationId, String userId) {
		
		return dao.cancelRequest(registrationId, userId);
	}

	

	

}
