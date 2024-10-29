package business;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
//    CREATE TABLE artists
//            (
//                    artistId INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
//                    name     VARCHAR(30) NOT NULL
//);
    // Fields
    private int artistID;
    private String artistName;



}
