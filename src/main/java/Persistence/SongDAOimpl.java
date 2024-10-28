package Persistence;

import business.Song;

import java.sql.*;

public class SongDAOimpl implements SongDAO{

    @Override
    public Song getById(int id) {
        Song song = null;

        // database connection details
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/musiclibrary";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                // Prepare the SQL statement
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs WHERE songId = ?")) {
                    // Fill in the blanks, i.e. parameterize the query
                    ps.setInt(1, id);
                    // TRY to execute the query
                    try (ResultSet rs = ps.executeQuery()) {
                        // Extract the information from the result set
                        if (rs.next()) {
                            // Get the pieces of a song from the resultset and create a new Song
                            song = new Song(
                                    rs.getInt("songId"),
                                    rs.getString("title"),
                                    rs.getInt("albumId"),
                                    rs.getInt("artistId"),
                                    rs.getString("additionalInfo")
                            );
                        }
                    } catch (SQLException e) {
                        System.out.println("SQL Exception occurred when executing SQL or processing results.");
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when attempting to connect to the database.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred when trying to load driver: " + e.getMessage());
            e.printStackTrace();
        }

        return song;
    }
}