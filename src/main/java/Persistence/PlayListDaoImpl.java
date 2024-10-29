package Persistence;

import business.PlayList;
import business.Song;
import business.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the PlayListDAO interface
 * to manage PlayList records in database.
 */
public abstract class PlayListDaoImpl extends Song implements PlayListDao{

    private final Connection connection;

    /**
     * Constructs a PlayListDAOImpl with the specified database connection.
     * @param conn is the Connection object to connect to the database.
     */
    public PlayListDaoImpl(Connection conn) {
        this.connection = conn;
    }



    /**
     * Constructs a UserDAOImpl with the specified database connection.
     * @param conn is the Connection object to connect to the database.
     */
    @Override
    public PlayList getPlayListById(int id) {
        String sql = "SELECT * FROM playlist WHERE playlistId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PlayList(
                            rs.getInt("playlistId"),
                            rs.getInt("userId"),
                            rs.getString("name"),
                            rs.getInt("type")

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
    @Override
    public boolean deletePlayListById(int id) {
        int rowsAffected = 0;
        String sqlQuery = "DELETE  FROM playlist WHERE playlistId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
                System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();

        } finally {
            // Close the connection
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        }

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
}
