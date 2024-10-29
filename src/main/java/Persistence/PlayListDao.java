package Persistence;

import business.Song;

import java.util.List;

public interface PlayListDao {
    public  boolean addSongToPlayList(Song s);
    public  boolean deleteSongFromPlatList(int songId);
    public List<Song> getAllSongsOnPlayList();
}
