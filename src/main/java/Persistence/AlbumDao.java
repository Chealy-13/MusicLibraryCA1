package Persistence;
/**
 *
 * @author Chris
 *
 */
import business.Album;

import java.util.List;

public interface AlbumDao {
    public Album getByAlbumId(int albumId);

    List<Album> getAlbumsByArtistId(int artistId);
}
