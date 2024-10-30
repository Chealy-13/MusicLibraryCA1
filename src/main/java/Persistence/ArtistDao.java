package Persistence;

import business.Artist;

import java.util.List;

public interface ArtistDao {
    Artist getArtistByArtistId(int artistId);

    List<Artist> getAllArtists();
}
