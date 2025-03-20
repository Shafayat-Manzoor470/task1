package com.webkorps.task1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.webkorps.task1.entity.User;
import com.webkorps.task1.service.AddUserClass;
import com.webkorps.task1.service.UserNotFound;
import com.webkorps.task1.service.UserService;

public class AppMain {

	private static Scanner sc = new Scanner(System.in);
	private static UserService userService = new UserService();
	private static AddUserClass addUserClass = new AddUserClass(userService);

	public static void main(String[] args) throws UserNotFound {

		boolean run = true;

		while (run) {

			System.out.println("1.Add user");
			System.out.println("2.Delete user");
			System.out.println("3.Fetch single user");
			System.out.println("4.Fetch all users");
			System.out.println("5.Update user");
			System.out.println("6.Search user");
			System.out.println("7 Exit");
			System.out.println("Enter you choice");

			int choice = sc.nextInt();

			switch (choice) {
//========================Add user================================================
			case 1: {
				Optional<String> user = addUserClass.createUser();
				System.out.println(user.orElse("Something went wrong"));

			}break;
//======================Delete User================================================
			case 2: {

				System.out.println("Enter email");
				String email = sc.next();
				try {
					userService.deleteUser(email);
				} catch (UserNotFound e) {
					System.out.println(e.getMessage());
				}

			}break;
			
//======================Fetch Single User================================================			
			case 3: {

				System.out.println("Enter email");
				String email = sc.next();
				Optional<User> fetchSingleUser = userService.fetchSingleUser(email);
				if(fetchSingleUser.isPresent())
					System.out.println(fetchSingleUser.get());
				else
					throw new UserNotFound("User not found");

			}break;

//======================Fetch All Users================================================
			case 4: {

				List<User> fetchAllUsers = userService.fetchAllUsers();
				if (fetchAllUsers.isEmpty()) {
					throw new UserNotFound("User not found");
				}
				for (User user : fetchAllUsers) {
					System.out.println(user);

				}
			}break;

//======================Update User================================================
			case 5: {

				System.out.println("Enter email");
				String email = sc.next();
				System.out.println("Enter new Name");
				String newName = sc.next();
				System.out.println("Enter new mobile numbers seperated by comma");
				List<String> newMobile = Arrays.asList(sc.next().split(","));
				try {
					userService.updateUser(email, newName, newMobile);
				} catch (UserNotFound e) {
					System.out.println(e.getMessage());
				}

			}break;

//======================Search User================================================
			case 6: {
				System.out.println("Enter Keyword");
				Set<User> fetchUser = userService.fetchUser(sc.next());
				if (!fetchUser.isEmpty()) {
					for (User user : fetchUser) {
						System.out.println(user);
						
					}
				}
				else
					throw new UserNotFound("User not found");
			}break;
			
//=========================Exit===============================================================
			case 7: {

				System.out.println("Thank you");
				run = false;
			}break;

//=================For invalid choice=========================================================
			default:
				System.out.println("Invalid choice");
			}

		}

	}

}
