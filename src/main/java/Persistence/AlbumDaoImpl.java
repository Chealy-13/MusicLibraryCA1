package Persistence;

import business.Album;
import business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends MySQLDao implements AlbumDao {

    public AlbumDaoImpl(String propertiesFilename) {
        super(propertiesFilename);
    }

    @Override
    public Album getByAlbumId(int albumId) {
        Album album = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM albums where albumId = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, albumId);
            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if (rs.next()) {
                    album = mapAlbum(rs);
                    album.setSongs(getSongsForAlbum(albumId));
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
        return album;
    }

    private List<Song> getSongsForAlbum(int albumId) throws SQLException {
        List<Song> songs = new ArrayList<>();
        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs WHERE albumId = ?")) {
            ps.setInt(1, albumId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int songId = rs.getInt("songId");                // Ensure songId is an int
                    String songTitle = rs.getString("songTitle");     // Ensure title is a String
                    int artistId = rs.getInt("artistId");             // Ensure artistId is an int
                    String additionalInfo = rs.getString("additionalInfo"); // Ensure additionalInfo is a String

                    // Construct the Song object
                    Song song = new Song(songId, songTitle, albumId, artistId, additionalInfo);
                    songs.add(song);
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

    private Album mapAlbum(ResultSet rs) throws SQLException {
        return Album.builder()
                .albumId(rs.getInt("albumId"))
                .albumTitle(rs.getString("albumTitle"))
                .artistId(rs.getInt("artistId"))
                .releaseYear(rs.getInt("releaseYear"))
                .build();
    }
}
