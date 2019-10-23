package com.capgemini.librarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.dao.AdminDAO;

@Service
public class AdminServicesImpl implements AdminServices {
	
	@Autowired
	private AdminDAO dao;

	@Override
	public Boolean registerStudents(Users user) {
		return dao.registerStudents(user);
	}

	@Override
	public Boolean modifyStudents(Users user) {
		return dao.modifyStudents(user);
	}

	@Override
	public Boolean deleteStudents(String userId) {
		return dao.deleteStudents(userId);
	}

	@Override
	public Users searchStudents(String user_id) {
		return dao.searchStudents(user_id);
	}

	@Override
	public Boolean modifyLibrarian(Users user) {
		return dao.modifyLibrarian(user);
	}

	@Override
	public Users searchLibrarian(String userId) {
		return dao.searchLibrarian(userId);
	}

	@Override
	public Boolean deleteLibrarian(String userId) {
		return dao.deleteLibrarian(userId);
	}

	

}
