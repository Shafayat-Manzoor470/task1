package com.webkorps.task1.service;

import java.util.*;

public class AddUserClass {

	private static Scanner sc = new Scanner(System.in);
	private static UserService userService;

	public AddUserClass(UserService userService) {
		this.userService = userService;

	}

//=====================Add User=============================================
	public Optional<String> createUser() {
		char ch = ' ';
		System.out.println("Enter name:");
		String name = sc.next();

//================================== validating email==============================================
		System.out.println("Enter email:");
		String email = sc.next();
		while (!userService.emailChecker(email)) {    //===============outer loop
			
			System.out.println("Invalid email id");

			while (true) {     //===============================inner loop
				
				System.out.println("Would you like to enter the email again? Enter Y or N:");
				ch = sc.next().charAt(0);

				if (ch == 'Y' || ch == 'y') {

					System.out.println("Enter the new email id:");
					email = sc.next();
					break;
				} else if (ch == 'N' || ch == 'n') {
					return Optional.of("invalid email");
				} else {
					System.out.println("Please enter a valid choice: Y or N");

				}
			}   //================inner loop ends
			
		}   //==================outer loop ends
		
		
//================================= validating mobile number=======================================
		System.out.println("Enter mobile numbers (comma-separated):");
		List<String> mobile = Arrays.asList(sc.next().split(","));
		while (!userService.mobileChecker(mobile)) {   //===============outer loop
			System.out.println("invalid mobile number");
			System.out.println("Would you like to enter the mobile number again? Enter Y or N:");
			char ch1 = sc.next().charAt(0);
			
			  while (true) {   //===============================inner loop
				  
				if (ch1 == 'Y' || ch1 == 'y') {
					System.out.println("Enter mobile numbers (comma-separated):");
					mobile = Arrays.asList(sc.next().split(","));//=====accepts mobile numbers separated by comma
					break;
					
				} else if (ch1 == 'N' || ch1 == 'n') {
					return Optional.of("Invalid mobile  number");
					
				} else {
					System.out.println("Please enter a valid choice: Y or N");
					break;

				}

			}  //================inner loop ends

		}	//==================outer loop ends
		return userService.addUser(email, name, mobile);
		
	}
}
