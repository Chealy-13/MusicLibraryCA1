package com.example.musiclibrary;

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

	private static void welcomeApp() {
		MusicLibraryApplication a = new MusicLibraryApplication();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Register: ");
			System.out.println("2. Login: ");
			System.out.println("3. ");
			System.out.println("4. ");
			System.out.println("5. ");

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
		String cvv1 = scanner.nextLine();
		if (userDAO.validateCCInfo(cardNum, expireD, cvv1)) {

			if (userDAO.RegisterU(username, password, email)) {
				System.out.println("Registration successful!");
			} else {
				System.out.println("Unsuccessful!, please try again!");
			}
		} else {
			System.out.println("Invalid card credentials! Please try again.");
		}
	}

	public void LoginU() {
		System.out.println("Login to account: ");

		System.out.println("Please enter your username: ");
		String username = scanner.nextLine();

		System.out.println("Please enter your passwords: ");

		String password = scanner.nextLine();
		user user = userDAO.LoginU(username);
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("Welcome " + username);
		} else {
			System.out.println("Invalid credentials! Please try again!");
		}
	}

}