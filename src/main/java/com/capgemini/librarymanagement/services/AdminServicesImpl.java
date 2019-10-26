package com.capgemini.librarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.dao.AdminDAO;
import com.capgemini.librarymanagement.utility.Validation;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	private Validation validation;

	@Autowired
	private AdminDAO dao;

	@Override

	public Boolean registerStudents(Users user) {
		validation.regexId(user.getUserId());
		validation.regexEmail(user.getEmailId());
		validation.regexPassword(user.getPassword());
		validation.regexName(user.getUserName());
		return dao.registerStudents(user);
	}

	@Override
	public Boolean modifyStudents(Users user) {
		validation.regexId(user.getUserId());
		validation.regexEmail(user.getEmailId());
		validation.regexPassword(user.getPassword());
		validation.regexName(user.getUserName());
		return dao.modifyStudents(user);
	}

	@Override
	public Boolean deleteStudents(String userId) {
		validation.regexId(userId);
		return dao.deleteStudents(userId);
	}

	@Override
	public Users searchStudents(String user_id) {
		validation.regexId(user_id);
		return dao.searchStudents(user_id);
	}

	@Override
	public Boolean modifyLibrarian(Users user) {
		validation.regexId(user.getUserId());
		validation.regexEmail(user.getEmailId());
		validation.regexPassword(user.getPassword());
		validation.regexName(user.getUserName());
		return dao.modifyLibrarian(user);
	}

	@Override
	public Users searchLibrarian(String userId) {
		validation.regexId(userId);
		return dao.searchLibrarian(userId);
	}

	@Override
	public Boolean deleteLibrarian(String userId) {
		validation.regexId(userId);
		return dao.deleteLibrarian(userId);
	}

	@Override
	public Boolean addLibrarian(Users user) {
		validation.regexId(user.getUserId());
		validation.regexEmail(user.getEmailId());
		validation.regexPassword(user.getPassword());
		validation.regexName(user.getUserName());
		return dao.addLibrarian(user);
	}



}
