package com.capgemini.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagement.beans.UserResponse;
import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.services.AdminServices;

@RestController
public class AdminController {
	
	@Autowired
	private AdminServices service;
	
	@PostMapping("/addStudent")
	public UserResponse  registerStudents(@RequestBody Users student,ModelMap map) {
		System.out.println(student);
		UserResponse response=new UserResponse();
		if(service.registerStudents(student)) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return response;

	}//end of registerStudents
	
	@PostMapping("/updateStudent")

	public Boolean modifyStudents(@RequestBody Users student,ModelMap map) {
		UserResponse response=new UserResponse();
		Boolean user=service.modifyStudents(student);
		if(user) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return user;
	}//end of modifyStudents
	
	@PostMapping("/deleteStudent")

	public Boolean deleteStudents(@RequestBody Users student,ModelMap map) {
		System.out.println(student.getUserId());
		UserResponse response=new UserResponse();
		Boolean student1=service.deleteStudents(student.getUserId());
		if(student1) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return student1;


	}//end of deleteStudents

	@GetMapping("/searchStudents")
	public UserResponse SearchStudents(@RequestBody String userId,ModelMap map) {

		UserResponse response=new UserResponse();
		try {
			Users student = service.searchStudents(userId);
			if(student!=null) {
				response.setStatusCode(201);
				response.setMessage("success");
				
			}else {
				response.setStatusCode(404);
				response.setMessage("failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return response;

	}//end of searchStudents
	
	@PostMapping("/updateLibrarian")

	public Boolean modifyLibrarian(@RequestBody Users librarian,ModelMap map) {
		UserResponse response=new UserResponse();
		Boolean librarian1=service.modifyLibrarian(librarian);
		if(librarian1) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return librarian1;
	}//end of modifyLibrarian
	
	@PostMapping("/deleteLibrarian")

	public Boolean deleteLibrarian(@RequestBody Users librarian,ModelMap map) {
		System.out.println(librarian.getUserId());
		UserResponse response=new UserResponse();
		Boolean librarian1=service.deleteStudents(librarian.getUserId());
		if(librarian1) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return librarian1;


	}//end of deleteStudents
	
	@GetMapping("/searchLibrarian")
	public UserResponse SearchLibrarian(@RequestBody String userId,ModelMap map) {

		UserResponse response=new UserResponse();
		try {
			Users librarian = service.searchStudents(userId);
			if(librarian!=null) {
				response.setStatusCode(201);
				response.setMessage("success");
				
			}else {
				response.setStatusCode(404);
				response.setMessage("failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return response;

	}//end of searchLibrarian
	
}
