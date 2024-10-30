package Persistence;

import business.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDaoImpl extends MySQLDao implements ArtistDao {

    public ArtistDaoImpl(String propertiesFilename) {
        super(propertiesFilename);
    }


    private Artist mapRow(ResultSet rs) throws SQLException {
        return Artist.builder()
                .artistID(rs.getInt("artistId"))
                .artistName(rs.getString("name"))
                .build();
    }
}
