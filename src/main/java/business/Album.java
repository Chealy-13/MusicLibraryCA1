package business;

import java.util.Date;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
//    CREATE TABLE album
//            (
//                    albumId      INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
//                    title        VARCHAR(50)  NOT NULL,
//    artistId     INT UNSIGNED NOT NULL,
//    releaseDate  DATE         NOT NULL,
//    ratingOfSongs INT UNSIGNED NOT NULL,
//
//    CONSTRAINT album_artist_fk FOREIGN KEY (artistId) REFERENCES artists(artistId)
//            );

    private int albumID;
    private String albumTitle;
    private int artistID;
    private Date releaseDate;
    private int rating;
}
