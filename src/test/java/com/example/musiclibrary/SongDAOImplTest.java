package com.example.musiclibrary;

import Persistence.SongDao;
import Persistence.SongDaoImpl;
import business.Song;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class SongDAOImplTest {
    @org.junit.jupiter.api.Test
    void getAllEmployees_AllFound() {
        SongDao songDao = new SongDaoImpl("test_musiclibrary");

        List<Song> expected = generateAllResults();
        List<Song> results = songDao.getAllSongs();
        assertEquals(6, results.size());

        for (int i = 0; i < results.size(); i++) {
            assertSongEquals(expected.get(i),results.get(i));
        }
    }

    void assertSongEquals(Song expected, Song result){
        assertEquals(expected.getSongID(), result.getSongID());
        assertEquals(expected.getSongTitle(), result.getSongTitle());
        assertEquals(expected.getArtistID(), result.getArtistID());
        assertEquals(expected.getAlbumID(), result.getAlbumID());
        assertEquals(expected.getInfo(), result.getInfo());
    }

    List<Song> generateAllResults() {
        List<Song> results = new ArrayList<>();

        Song s1 = Song.builder()
                .songID(1)
                .songTitle("Yellow")
                .artistID(101)
                .albumID(201)
                .info("alt/rock by Coldplay")
                .build();

        Song s2 = Song.builder()
                .songID(2)
                .songTitle("Bohemian Rhapsody")
                .artistID(102)
                .albumID(202)
                .info("Iconic song by Queen")
                .build();

        Song s3 = Song.builder()
                .songID(3)
                .songTitle("Stairway to Heaven")
                .artistID(103)
                .albumID(203)
                .info("Rock song by Led Zeppelin")
                .build();

        Song s4 = Song.builder()
                .songID(4)
                .songTitle("Hotel California")
                .artistID(104)
                .albumID(204)
                .info("Famous song by Eagles")
                .build();

        Song s5 = Song.builder()
                .songID(5)
                .songTitle("Hey Jude")
                .artistID(105)
                .albumID(205)
                .info("Classic song by The Beatles")
                .build();

        Song s6 = Song.builder()
                .songID(6)
                .songTitle("Lonely Island")
                .artistID(106)
                .albumID(206)
                .info("Folk/Trad song by Amble")
                .build();

        results.add(s1);
        results.add(s2);
        results.add(s3);
        results.add(s4);
        results.add(s5);
        results.add(s6);
        return results;
    }
}
