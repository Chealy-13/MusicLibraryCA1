package Persistence;

import business.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDaoImpl extends MySQLDao implements SongDao {

    public SongDaoImpl(String propertiesFilename) {
        super(propertiesFilename);
    }

//    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDaoImpl("database.properties");
//        List<Customer> customers = customerDao.getAllCustomers();
//        System.out.println(customers);
//        System.out.println("------------------------------");
//        System.out.println("Customer with id 119: " + customerDao.getById(119));
//    }

    @Override
    public Song getBySongId(int songId) {
        Song song = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where songId = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, songId);
                    // TRY to execute the query
                    try (ResultSet rs = ps.executeQuery()) {
                        // Extract the information from the result set
                        // Use extraction method to avoid code repetition!
                        if(rs.next()) {
                            song = mapRow(rs);
                        }
                    } catch (SQLException e) {
                        System.out.println("SQL Exception occurred when executing SQL or processing results.");
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }
        return song;
    }


    @Override
    public List<Song> getAllSongs() {
        // Create variable to hold the customer info from the database
        List<Song> songs = new ArrayList<>();

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // Get a statement from the connection
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs")) {
            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Repeatedly try to get a customer from the resultset
                while (rs.next()) {
                    Song s = mapRow(rs);
                    songs.add(s);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }

        return songs;
    }

    @Override
    public Song getByArtistId(int artistId) {
        Song song = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where artistId = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, artistId);
            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()) {
                    song = mapRow(rs);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }
        return song;
    }

    @Override
    public boolean deleteBySongId(int songId) {
        int rowsAffected = 0;

        Connection conn = super.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM songs where songId = ?")) {
            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, songId);

            // Execute the operation
            // Remember that when you are doing an update, a delete or an insert,
            // your only result will be a number indicating how many rows were affected
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare/execute SQL.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the superclass method
            super.freeConnection(conn);
        }

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addSong(Song song) {
        int rowsAffected = 0;

        // Get a connection using the superclass
        Connection conn = super.getConnection();

        String sql = "INSERT INTO songs (songTitle, albumId, artistId, additionalInfo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, song.getSongTitle());
            ps.setInt(2, song.getAlbumID());
            ps.setInt(3, song.getArtistID());
            ps.setString(4, song.getInfo());

            rowsAffected = ps.executeUpdate();

            // If we want to capture the generated song ID, we can retrieve it here
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        song.setSongID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while adding the song.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the superclass method
            super.freeConnection(conn);
        }
        return rowsAffected > 0;
    }

    private Song mapRow(ResultSet rs) throws SQLException {
        // Get the pieces of a customer from the resultset and create a new Customer
        Song s = Song.builder()
                .songID(rs.getInt("songId"))
                .songTitle(rs.getString("songTitle"))
                .albumID(rs.getInt("albumId"))
                .artistID(rs.getInt("artistId"))
                .info(rs.getString("additionalInfo"))
                .build();
        // Return the extracted Customer (or null if the resultset was empty)
        return s;
    }
}