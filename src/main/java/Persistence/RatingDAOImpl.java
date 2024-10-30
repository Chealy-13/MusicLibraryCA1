package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RatingDAOImpl implements RatingDAO {
    private Connection connection;

    public RatingDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean rateSong(int songId_, int userId_, int rating_) throws SQLException {
        String sql = "INSERT INTO Ratings (songId, userId, rating) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, songId_);
            ps.setInt(2, userId_);
            ps.setInt(3, rating_);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
