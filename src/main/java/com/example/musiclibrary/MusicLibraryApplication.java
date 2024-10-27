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

	private userDAO UserDAO;

	public MusicLibraryApplication() {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			UserDAO = new userDAOImpl(connection) {
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   public static void main(String[] args) {
		MusicLibraryApplication app = new MusicLibraryApplication();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Register: ");
			System.out.println("2. Login: ");
			System.out.println("3: ");
			System.out.println("4. : ");
			System.out.println("5. : ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			 }
		
   }
}
