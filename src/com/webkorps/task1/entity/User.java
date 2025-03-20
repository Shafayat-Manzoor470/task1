package com.webkorps.task1.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private String email;
	private List<String> mobile;

	public User() {
		
	}

	public User(String email, String name, List<String> mobile) {
		this.email = email;
		this.name = name;
		this.mobile = new ArrayList<String>(mobile);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getMobile() {
		return mobile;
	}

	public void setMobile(List<String> mobile) {
		this.mobile = new ArrayList<String>(mobile);
	}

	@Override
	public String toString() {
		return "name=" + name + ", email=" + email + ", mobile=" + mobile;
	}

}
