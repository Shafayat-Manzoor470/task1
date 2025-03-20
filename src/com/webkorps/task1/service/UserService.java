package com.webkorps.task1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import com.webkorps.task1.entity.User;

public class UserService {

	Scanner scanner = new Scanner("System.in");
	Map<String, User> users = new HashMap();

	private final Pattern emailPattern = Pattern.compile(
			"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[._@-])[A-Za-z\\d._-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}$",
			Pattern.CASE_INSENSITIVE);

	private final Pattern mobilePattern = Pattern.compile("^\\d{10}$", Pattern.CASE_INSENSITIVE);
	
	
	
	
//=========================added User===============================================================
	public Optional<String> addUser(String email, String name, List<String> mobile) {

//==========Checking if user is already present or not===================================
		if (users.containsKey(email)) {
			System.out.println("User already exists");
			return Optional.empty();

		}
		
//============validating email=========================================================
		if (!emailPattern.matcher(email).matches()) {
			System.out.println("invalid email id");
			return null;

		}
		
//==================validating mobile number====================================
		for (String phone : mobile) {
			if (!mobilePattern.matcher(phone).matches()) {
				System.out.println("Invalid mobile");
				return Optional.empty();

			}
		}

		users.put(email, new User(email, name, mobile));
		return Optional.of("User added");

	}//========addUser() ends=========================
	
	
	
//===========================fetch All Users==========================================================
	public List<User> fetchAllUsers() {
		return new ArrayList<User>(users.values());
	}
	
//===========================fetch Single Users==========================================================
	public Optional<User> fetchSingleUser(String email) {
		if (!users.containsKey(email))
			return Optional.empty();

		return Optional.of(users.get(email));
	}

	
	
//=========================Search User==================================================================
	public Set<User> fetchUser(String keyword) {

		Set<User> matchedUsers= new HashSet<User>();
		for (User user : users.values()) {
			if (user.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
				matchedUsers.add( user);
			}

			if (user.getName().toLowerCase().contains(keyword.toLowerCase())) {
				matchedUsers.add( user);
			}

			for (String mobileNum : user.getMobile()) {
				if (mobileNum.contains(keyword)) {
					matchedUsers.add( user);
				}
			}
		}

		return  matchedUsers;
	}


//====================================Delete User===================================================	
	public void deleteUser(String email) throws UserNotFound {
		if (!users.containsKey(email))
			throw new UserNotFound("User not found");

		users.remove(email);
		System.out.println("Deleted");

	}

	
	
//=============================Update User============================================================
public void updateUser(String email, String newName, List<String> newMobile) throws UserNotFound {

		if (users.containsKey(email)) {
			User user = users.get(email);
			user.setName(newName);
			user.setMobile(newMobile);
			System.out.println("User updatd Succcessfully");
		} else
			throw new UserNotFound("User not found");

	}

	
	
//======================Email Validation While accepting email======================================	
	public boolean emailChecker(String email) {
		if (!emailPattern.matcher(email).matches())
				
		     return false;
		
		return true;
		     

	}
	
	
	
//=====================Mobile number validation while accepting=====================================	
	public boolean mobileChecker( List<String> mobile) {
		for (String phone : mobile) {
			if (!mobilePattern.matcher(phone).matches()) {
				return false;
			}
				
					
		}

           return true;
		
	}
}
