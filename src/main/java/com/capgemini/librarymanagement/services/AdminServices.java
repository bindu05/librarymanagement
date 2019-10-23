package com.capgemini.librarymanagement.services;

import com.capgemini.librarymanagement.beans.Users;

public interface AdminServices {

	public Boolean registerStudents(Users user);
	public Boolean modifyStudents(Users user);
	public Boolean deleteStudents(String userId);
	public Users searchStudents(String userId);
	
	public Boolean modifyLibrarian(Users user);
	public Boolean deleteLibrarian(String userId);
	public Users searchLibrarian(String userId);
	

}
