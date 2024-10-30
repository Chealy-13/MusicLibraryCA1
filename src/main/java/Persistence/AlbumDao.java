package Persistence;

import business.Album;

import java.util.List;

public interface AlbumDao {
    public abstract Album getByAlbumId(int albumId);

    List<Album> getAlbumsByArtistId(int artistId);
}
