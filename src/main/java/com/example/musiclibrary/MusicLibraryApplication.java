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

	private userDAO userDAO;

	public MusicLibraryApplication() {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			userDAO = new userDAOImpl(connection) {
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void register() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Register user: ");

		System.out.println("Please create a username:  ");
		String username = scanner.nextLine();

		System.out.println("Please create a password: ");
		String password = scanner.nextLine();

		System.out.println("Please provide an email address: ");
		String email = scanner.nextLine();

		user newU = new user(0, username, password, email);
		if (userDAO.save(newU)) {
			System.out.println("Registration successful!");
		} else {
			System.out.println("Unsuccessful registration, please try again!");
		}
	}



   public static void main(String[] args) {
		MusicLibraryApplication a = new MusicLibraryApplication();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Register: ");
			System.out.println("2. Login: ");
			System.out.println("3: ");
			System.out.println("4. : ");
			System.out.println("5. : ");

			int choice1 = scanner.nextInt();
			scanner.nextLine();

			switch (choice1) {
				case 1:
					a.register();
					break;
			}
		}
   }
}
