package business;
import lombok.*;
// Add getter methods
@Getter
// Add setter methds
@Setter
// Add a toString method
@ToString
// Add equals and hashcode methods - only include the specifically noted variables
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Add the ability to build object with any components in any order
@Builder
// Add an all-args constructor
@AllArgsConstructor

public class Song implements Comparable<Song>{
//    CREATE TABLE songs
//            (
//                    songId         INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
//                    title          VARCHAR(50)  NOT NULL,
//    albumId        INT UNSIGNED,
//    artistId       INT UNSIGNED,
//    additionalInfo VARCHAR(100),
//
//    CONSTRAINT fk_song_album FOREIGN KEY (albumId) REFERENCES album(albumId),
//
//    CONSTRAINT fk_song_artist FOREIGN KEY (artistId) REFERENCES artists(artistId)
//            );
    @EqualsAndHashCode.Include
    private int songID;

    @NonNull
    private int albumID;

    @NonNull
    private int artistID;

    @NonNull
    private String songTitle;

    @NonNull
    private String info;

    //AlbumDaoImpl getSongsForAlbum method not recognising songtitle or albumids type
    // so had to generate constructor
    public Song(int songId, String songTitle, int albumId, int artistId, String additionalInfo) {
    }

    @Override
    public int compareTo(Song s) {
        if (songID < s.songID) {
            return -1;
        } else if (songID > s.songID) {
            return 1;
        }
        return 0;
    }

    public String format() {
        return "Song ID: " + songID +
                "\nSong Title: " + songTitle +
                "\nAlbum ID: " + albumID +
                "\nArtist ID: " + artistID +
                "\nInfo: " + info;
    }
}
