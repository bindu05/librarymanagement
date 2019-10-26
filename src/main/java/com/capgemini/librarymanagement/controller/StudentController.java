package com.capgemini.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.UserResponse;
import com.capgemini.librarymanagement.services.StudentsServices;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*" ,allowCredentials="true" )
public class StudentController {
	
	@Autowired
	private StudentsServices service;
	
	@PostMapping("/requestBook")

	public BooksRegistration requestBook(@RequestBody BooksInventory book, String userId) {
		UserResponse response = new UserResponse();
		BooksRegistration req = service.requestBook(book,userId);

		if (req != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return req;
	}// end of requestBook

}
