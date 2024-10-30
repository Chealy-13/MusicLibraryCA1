package Persistence;

import business.Artist;

public interface ArtistDao {
    Artist getArtistByArtistId(int artistId);
}
