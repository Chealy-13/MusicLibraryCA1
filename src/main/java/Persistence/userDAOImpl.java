package Persistence;

import business.user;
import org.apache.catalina.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class userDAOImpl implements userDAO {
    private final Connection connection;

    public userDAOImpl(Connection con) {
        this.connection = con;
    }

    @Override
    public user usernames(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new user(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );

            }
        } catch (SQLException E) {
        }
        return null;
    }

@Override
    public boolean save(user u) {

        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement state = connection.prepareStatement(sql)) {
            state.setString(1, u.getUser_name());
            state.setString(2, u.getPassword());
            state.setString(3, u.getEmail());
            return state.executeUpdate() > 0;
        } catch (SQLException E) {
            E.printStackTrace();
        }
        return false;
    }
}




