package Persistence;

import business.Song;

import java.util.List;

public interface SongDAO {
    Song getById(int id);

    List<Song> getAllSongs();

    Song getByArtistId(int artistId);
    // add song
    //delete song

}
