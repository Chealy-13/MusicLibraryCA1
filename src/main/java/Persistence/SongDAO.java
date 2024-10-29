package Persistence;

import business.Song;

import java.util.List;

public interface SongDAO {
    Song getBySongId(int songId);

    List<Song> getAllSongs();

    Song getByArtistId(int artistId);

    boolean deleteBySongId(int songId);

    boolean addSong(Song song);

}
