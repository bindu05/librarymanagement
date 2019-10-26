package com.capgemini.librarymanagement.utility;

public interface Validation {

	public  Integer regexId (String id);
	public String regexEmail (String email);
	public String regexPassword (String password);
	public String regexName(String name);

}
