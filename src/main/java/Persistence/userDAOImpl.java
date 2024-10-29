package Persistence;

import business.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the UserDAO interface
 * to manage user records in database.
 */
public class userDAOImpl implements userDAO {
    private final Connection connection;

    /**
     * Constructs a UserDAOImpl with the specified database connection.
     * @param con is the Connection object to connect to the database.
     */
    public userDAOImpl(Connection con) {
        this.connection = con;
    }

    /**
     * Collects a user from the database by username,
     * @return a User object if found, or if no matching user is found.
     */
    @Override
    public user usernames(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new user(
                            rs.getInt("userId"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email")
                    );
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();

            }
        } catch (SQLException e) {
            System.out.println("ClassNotFoundException occurred when trying to load driver: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Saves a new user to the database,
     * @param u User object is saved.
     * @return true if user is successfully saved, if not false.
     */
    @Override
    public boolean save(user u) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement state = connection.prepareStatement(sql)) {
            state.setString(1, u.getUser_name());
            state.setString(2, u.getPassword());
            state.setString(3, u.getEmail());
            return state.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Deletes user based on username from database
     * @param username of user will be deleted
     * @return true if the user was successfully deleted, false otherwise or if an error occurred
     */
    @Override
    public boolean deleteByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement state = connection.prepareStatement(sql)) {
        state.setString(1, username);
        int rowChoice = state.executeUpdate();
        return rowChoice > 0;
    }
        catch(SQLException E){
        System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
        System.out.println("Error: " + E.getMessage());
        E.printStackTrace();
    }
        return false;
}
}

