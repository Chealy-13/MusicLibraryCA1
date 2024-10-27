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


        }




