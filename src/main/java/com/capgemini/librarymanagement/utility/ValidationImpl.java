package com.capgemini.librarymanagement.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

	@Override
	public Integer regexId(String id) {
		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(id);
		if(mat.matches()) {
			return Integer.parseInt(id);
		}else {
			return null;
		}
	}

	@Override
	public String regexEmail(String email) {
		Pattern pat = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		Matcher mat = pat.matcher(email);
		if(mat.matches()) {
			return email;
		}else {
			return null;
		}
	}


	@Override
	public String regexPassword(String password) {
		Pattern pat = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
		Matcher mat = pat.matcher(password);
		if(mat.matches()) {
			return password;
		}else {
			return null;
		}
	}

	@Override
	public String regexName(String name) {
		Pattern pat = Pattern.compile("\\w+");
		Matcher mat = pat.matcher(name);
		if(mat.matches()) {
			return name;
		}else {
			return null;
		}
	}

}
