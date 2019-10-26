package com.capgemini.librarymanagement.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.BooksTransaction;
import com.capgemini.librarymanagement.beans.UserResponse;
import com.capgemini.librarymanagement.services.LibrarianServices;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*" ,allowCredentials="true" )
public class LibrarianController {
	
	@Autowired
	private LibrarianServices service;
	
	@PostMapping("/addBook")

	public Boolean addBook(@RequestBody BooksInventory book,ModelMap map) {
		UserResponse response=new UserResponse();
		Boolean book1=service.addBook(book);
		if(book1) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return book1;

	}//end of addBook
	
	@PostMapping("/updateBook")
	public UserResponse updateBook(@RequestBody BooksInventory book,ModelMap map) {
		UserResponse response=new UserResponse();
		if(service.updateBook(book)) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return response;
	}//end of updateBook
	
	
	@PostMapping("deleteBook")
	public Boolean deleteBus(@RequestBody String bookId,ModelMap map) {
		UserResponse response=new UserResponse();
		Boolean book=service.deleteBook(bookId);
		if(book) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return book;


	}//end of deleteBook
	
	@GetMapping("/showAllIssuedBooksInfo")

	public UserResponse showAllIssuedBooksInfo(HttpSession session,ModelMap modelMap) {
		UserResponse response=new UserResponse();
		List<BooksTransaction> books=service.showAllIssuedBooksInfo();
		System.out.println(books);
		if (books!=null) {
			response.setStatusCode(201);
			response.setMessage("success");
			
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return response;

	} // End of showAllIssuedBooksInfo
	
	@GetMapping("/showAllRequestedBooksInfo")

	public UserResponse showAllRequestedBooks(HttpSession session,ModelMap modelMap) {
		UserResponse response=new UserResponse();
		List<BooksRegistration> books=service.showAllRequestedBooksInfo();
		System.out.println(books);
		if (books!=null) {
			response.setStatusCode(201);
			response.setMessage("success");
			
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return response;

	} // End of showAllRequestedBooksInfo
	
	@GetMapping("/showAllBooks")

	public UserResponse showAllBooks(HttpSession session,ModelMap modelMap) {
		UserResponse response=new UserResponse();
		List<BooksInventory> books=service.showAllBooks();
		System.out.println(books);
		if (books!=null) {
			response.setStatusCode(201);
			response.setMessage("success");
			
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return response;

	} // End of showAllBooks
	
	@PostMapping("/addFine")

	public BooksTransaction addFine(@RequestBody String registrationId, Date returnDate, ModelMap map) {
		UserResponse response=new UserResponse();
		BooksTransaction fine=service.addFine(registrationId, returnDate);
		if(fine != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return fine;

	}//end of addFine
	
	@PostMapping("/acceptRequest")

	public BooksTransaction acceptRequest(@RequestBody String registrationId,ModelMap map) {
		UserResponse response=new UserResponse();
		BooksTransaction req=service.acceptRequest(registrationId);
		if(req != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		}else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}return req;

	}//end of acceptRequest

}
