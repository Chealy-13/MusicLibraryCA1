DROP DATABASE IF EXISTS musiclibary;
CREATE DATABASE IF NOT EXISTS musiclibary;

USE musiclibary;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    userId   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    test int null,
    username VARCHAR(50)         NOT NULL,
    password VARCHAR(100)        NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE artists
(
    artistId INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(30) NOT NULL
);

CREATE TABLE album
(
    albumId      INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(50)  NOT NULL,
    artistId     INT UNSIGNED NOT NULL,
    releaseDate  DATE         NOT NULL,
    ratingOfSongs INT UNSIGNED NOT NULL,

    CONSTRAINT album_artist_fk FOREIGN KEY (artistId) REFERENCES artists(artistId)
);


CREATE TABLE songs
(
    songId         INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(50)  NOT NULL,
    albumId        INT UNSIGNED,
    artistId       INT UNSIGNED,
    additionalInfo VARCHAR(100),

    CONSTRAINT fk_song_album FOREIGN KEY (albumId) REFERENCES album(albumId),

    CONSTRAINT fk_song_artist FOREIGN KEY (artistId) REFERENCES artists(artistId)
);

CREATE TABLE playlist
(
    playlistId INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId     INT UNSIGNED NOT NULL,
    name       VARCHAR(50),
    type       INT(2),

    CONSTRAINT fk_playlist_user FOREIGN KEY (userId) REFERENCES users(userId)
);