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
        //This SQL query selects all fields from the 'users' table where the username matches
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Sets the first parameter in the SQL query to the provided 'username'
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creates and returns a new 'user' object with data from the result set
                    return new user(
                            rs.getInt("userId"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email")
                    );
                }
                // Catches and prints SQL exceptions related to the inner try block (ResultSet operations)
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();

            }
            // Catches and prints SQL exceptions related to the outer try block (PreparedStatement operations)
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
        //This ine is used instert row to "users" table with values: useranme, password, email.
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement state = connection.prepareStatement(sql)) {
            // Set the first '?' placeholder in the SQL query to the user's username
            state.setString(1, u.getUser_name());
            // Set the second '?' placeholder in the SQL query to the user's password
            state.setString(2, u.getPassword());
            // Set the third '?' placeholder in the SQL query to the user's email
            state.setString(3, u.getEmail());
            return state.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        // Return false if fails or an exception occurs
        return false;

    }

    /**
     * Deletes user based on username from database
     * @param username of user will be deleted
     * @return true if the user was successfully deleted, false otherwise or if an error occurred
     */
    @Override
    public boolean deleteByUsername(String username) {
        //Sql delete query is used to remove a user with specified username
        String sql = "DELETE FROM users WHERE username = ?";
        //This line is used to stop sql injection and handle parameters safely
        try (PreparedStatement state = connection.prepareStatement(sql)) {
        //Username provided as a method parameter will replace the '?'
        state.setString(1, username);
        //This line runs the delete operation, it returns the number of rows affected
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

