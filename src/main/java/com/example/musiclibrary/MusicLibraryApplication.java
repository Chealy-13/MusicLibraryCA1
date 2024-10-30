package com.example.musiclibrary;
/**
 *
 * @author Damian
 *
 */
import Persistence.userDAO;
import Persistence.userDAOImpl;
import business.user;
import java.sql.*;
import java.util.Scanner;


public class MusicLibraryApplication {
	private static final String url = "jdbc:mysql://localhost:3306/musiclibary";
	private static final String username = "root";
	private static final String password = "";
	private static userDAOImpl userDAO;
	private static final Scanner scanner = new Scanner(System.in);
	boolean loggIn = true;

	public static void main(String[] args) {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musiclibary", "root", "");
			userDAO = new userDAOImpl(connection);
			welcomeApp();
		} catch (SQLException e) {
			System.out.println("Failed to connect to the database.");
			e.printStackTrace();
		}
	}

	/**
	 * Shows the welcome menu for users, gives option to register or login
	 */
	private static void welcomeApp() {
		MusicLibraryApplication a = new MusicLibraryApplication();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			//User can select options
			System.out.println("1. Register: ");
			System.out.println("2. Login: ");

			int choice1 = scanner.nextInt();
			scanner.nextLine();

			switch (choice1) {
				case 1:
					a.RegisterU1();
					break;
				case 2:
					a.LoginU();
					break;
			}
		}
	}

	public void RegisterU1() {
		//User input to register
		Scanner scanner = new Scanner(System.in);
		System.out.println("Register user: ");

		System.out.println("Please create a username:  ");
		String username = scanner.nextLine();

		System.out.println("Please create a password: ");
		String password = scanner.nextLine();

		System.out.println("Please provide an email address: ");
		String email = scanner.nextLine();

		System.out.print("Enter your credit card number: ");
		String cardNum = scanner.nextLine();

		System.out.print("Enter your expiry date (MM/YY): ");
		String expireD = scanner.nextLine();

		System.out.print("Enter your CVV: ");
		// Read the CVV from the user
		String cvv1 = scanner.nextLine();
		// Validate the give credit card info
		// using the validateCCInfo method in the userDAO object.
		if (userDAO.validateCCInfo(cardNum, expireD, cvv1)) {
			// If credit card info is valid, user will register
			// by calling the RegisterU method in the userDAO object.
			if (userDAO.RegisterU(username, password, email)) {
				// Prints success message.
				System.out.println("Registration successful!");
			} else {
				// Prints Unsuccessfully message.
				System.out.println("Unsuccessful!, please try again!");
			}
		} else {
			// Prints unsuccessfully message due to incorrect card info
			System.out.println("Invalid card credentials! Please try again.");
		}
	}

	public void LoginU() {
		System.out.println("Login to account: ");

		System.out.println("Please enter your username: ");
		String username = scanner.nextLine();

		System.out.println("Please enter your passwords: ");
		//Reads users password
		String password = scanner.nextLine();
		// looking for the user object from the database usings its username
		// by calling the LoginU method from the userDAO object
		user user = userDAO.LoginU(username);
		// Checks if user was found (user is not null)
		// then checks it the password matches, the password that was stored in user object
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("Welcome " + username);
			LogInMenu();
		} else {
			System.out.println("Invalid credentials! Please try again!");
		}
	}

	/**
	 * This method is where the user will be sent after login in successfully.
	 * The menu will give an option to logout.
	 */
	public void LogInMenu() {
		//Reads input from the console
		Scanner scanner = new Scanner(System.in);
		while (loggIn) {
			//Login menu options
			System.out.println("Login Menu");
			System.out.println("1. Logout");
			System.out.print("Choose an option: ");
			//Reads user input
			int choice = scanner.nextInt();
			//User choice
			switch (choice) {
				case 1:
					logout();
					return;
				default:
					//Notifies the user if invalid choice
					System.out.println("Invalid choice!");
			}
		}
	}
			public void logout() {
				//Message displayed when logged out
				System.out.println("You have successfully logged out.");
			}
	}
