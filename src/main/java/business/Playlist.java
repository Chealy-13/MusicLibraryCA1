package business;

public class Playlist {
    /*
    CREATE TABLE Playlist
(
    playlistId INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId     INT UNSIGNED NOT NULL,
    name       VARCHAR(50),
    type       INT(2),

    CONSTRAINT fk_playlist_user FOREIGN KEY (userId) REFERENCES users(userId)
);
    */
    // Fields
    private int playlistId;
    private int type;

    // Constructors
    public Playlist(){

    }

}
