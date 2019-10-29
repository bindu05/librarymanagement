package com.capgemini.librarymanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.UserResponse;
import com.capgemini.librarymanagement.beans.Users;
import com.capgemini.librarymanagement.services.CommonServices;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*" ,allowCredentials="true" )
public class CommonController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
	@Autowired
	private CommonServices service;
	
	@PostMapping("/login")

	public Users login(Users user) {
		
		UserResponse response=new UserResponse();
		Users user1=service.login(user.getUserId(), user.getPassword());
		
		if(user1 != null) {
			response.setStatusCode(201);
			response.setMessage("success");

		}else {
			response.setStatusCode(404);
			response.setMessage("failed");

		}	return user1;	
	}//end of login
	
	@GetMapping("showAllStudentsInfo")
	public List<Users> showAllStudentsInfo() {
		UserResponse response=new UserResponse();
		List<Users> students=service.showAllStudentsInfo();
		
		if (!students.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("success");

		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return students;
	} // End of showAllStudentsInfo
	
	@GetMapping("showAllLibrariansInfo")
	public List<Users> showAllLibrariansInfo() {
		UserResponse response=new UserResponse();
		List<Users> librarians=service.showAllLibrariansInfo();
		
		if (!librarians.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("success");

		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return librarians;
	} // End of showAllLibrariansInfo
	
	
	@GetMapping("/searchBookByAuthor")

	public UserResponse searchBookByAuthor(String author1, HttpSession session, ModelMap modelMap) {
		UserResponse response = new UserResponse();
		List<BooksInventory> books = service.searchBookByAuthor(author1);
		if (books != null) {
			response.setStatusCode(201);
			response.setMessage("success");
			
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return response;

	}
	
	@PostMapping("/cancelRequest")
	public Boolean cancelRequest(@RequestBody String registrationId, String userId, ModelMap map) {
		UserResponse response = new UserResponse();
		Boolean req = service.cancelRequest(registrationId,userId);
		if (req) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return req;

	}// end of cancelTicket

}
