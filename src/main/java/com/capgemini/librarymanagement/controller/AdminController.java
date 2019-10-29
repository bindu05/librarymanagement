package com.capgemini.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagement.beans.UserResponse;
import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.services.AdminServices;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*" ,allowCredentials="true" )
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
	
	@DeleteMapping("/deletestudent")
	public UserResponse deleteStudent(String userId) {
		UserResponse response = new UserResponse();
		if (service.deleteStudents(userId)) {
			response.setStatusCode(201);
			response.setMessage("success");
			response.setDescription("student deleted successfully..");
		} else {
			response.setStatusCode(401);
			response.setMessage("failed");
			response.setDescription("unable to delete student!");
		}
		return response;
	}
	@GetMapping("/searchStudent")
	public UserResponse SearchStudents(String userId,ModelMap map) {

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
	
	@DeleteMapping("/deletelibrarian")
	public UserResponse deleteLibrarian(String userId) {
		UserResponse response = new UserResponse();
		if (service.deleteLibrarian(userId)) {
			response.setStatusCode(201);
			response.setMessage("success");
			response.setDescription("librarian deleted successfully..");
		} else {
			response.setStatusCode(401);
			response.setMessage("failed");
			response.setDescription("unable to delete librarian!");
		}
		return response;
	}
	
	@GetMapping("/searchLibrarian")
	public UserResponse SearchLibrarian(String userId,ModelMap map) {

		UserResponse response=new UserResponse();
		try {
			Users librarian = service.searchLibrarian(userId);
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
	
	@PostMapping("/addLibrarian")
	public UserResponse  addLibrarian(@RequestBody Users librarian,ModelMap map) {
		System.out.println(librarian);
		UserResponse response=new UserResponse();
		if(service.addLibrarian(librarian)) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return response;

	}//end of addLibrarian
	
}
