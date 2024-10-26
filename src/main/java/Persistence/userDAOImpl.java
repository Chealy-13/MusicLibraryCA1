package Persistence;

import business.user;

import java.sql.Connection;

public abstract class userDAOImpl implements userDAO {
    private final Connection connection;

    public userDAOImpl(Connection con) {
        this.connection = con;
    }

    @Override
    public user usernames(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

    }
}



