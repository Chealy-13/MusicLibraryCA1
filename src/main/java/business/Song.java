package business;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Song {
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

    private int songID;
    private String songTitle;
    private int albumID;
    private int artistID;
    private String info;

}
