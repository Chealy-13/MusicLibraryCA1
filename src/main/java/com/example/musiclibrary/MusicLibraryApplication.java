package com.example.musiclibrary;

import Persistence.userDAO;
import Persistence.userDAOImpl;

import java.sql.*;


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
}