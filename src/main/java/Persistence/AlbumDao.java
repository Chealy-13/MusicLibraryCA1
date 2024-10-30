package Persistence;

import business.Album;

public interface AlbumDao {
    public abstract Album getByAlbumId(int albumId);
}
