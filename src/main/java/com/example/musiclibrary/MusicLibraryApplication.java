package com.example.musiclibrary;
/**
 * @author Damian Magiera + Chris
 */

import Persistence.*;
import business.Album;
import business.Artist;
import business.Song;
import business.user;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class MusicLibraryApplication {
    private static final String url = "jdbc:mysql://localhost:3306/musiclibary";
    private static final String username = "root";
    private static final String password = "";
    private static userDAOImpl userDAO;
    private static final Scanner scanner = new Scanner(System.in);
    boolean loggIn = true;
    private static ArtistDao artistDao;
    private static AlbumDao albumDao;
    private static SongDao songDao;

    public static void main(String[] args) {

        userDAO = new userDAOImpl();
        artistDao = new ArtistDaoImpl();
        albumDao = new AlbumDaoImpl();
        songDao = new SongDaoImpl();
        welcomeApp();

    }

    /**
     * The method runs in an infinite loop, allowing the user to perform.
     * actions until the application is terminated stopped.
     * Shows the welcome menu for users, gives option to register or login.
     */
    private static void welcomeApp() {
        MusicLibraryApplication a = new MusicLibraryApplication();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //User can select options
            System.out.println("1. Register: ");
            System.out.println("2. Login: ");

            int choice1 = scanner.nextInt();
            scanner.nextLine();

            switch (choice1) {
                case 1:
                    a.RegisterU1();
                    break;
                case 2:
                    a.LoginU();
                    break;
            }
        }
    }

    /**
     * Registering a user by gathering their information and validating credit card details.
     * The method asks the user for their username, password,
     * email address, and credit card information. It validates the credit
     * card details, if valid,
     * registers the user using the {RegisterU} method. If registration is successful,a message
     * displays; otherwise an error message is shown.
     **/
    public void RegisterU1() {
        //User input to register
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register user: ");

        //Gathering user username
        System.out.println("Please create a username:  ");
        String username = scanner.nextLine();

        //Gathering user password
        System.out.println("Please create a password: ");
        String password = scanner.nextLine();

        //Gathering user email address
        System.out.println("Please provide an email address: ");
        String email = scanner.nextLine();

        //Gathering user credit card number
        System.out.print("Enter your credit card number: ");
        String cardNum = scanner.nextLine();

        //Gathering user card expiry date
        System.out.print("Enter your expiry date (MM/YY): ");
        String expireD = scanner.nextLine();

        System.out.print("Enter your CVV: ");
        // Read the CVV from the user
        String cvv1 = scanner.nextLine();
        // Validate the give credit card info
        // using the validateCCInfo method in the userDAO object.
        if (userDAO.validateCCInfo(cardNum, expireD, cvv1)) {
            // If credit card info is valid, user will register
            // by calling the RegisterU method in the userDAO object.
            if (userDAO.RegisterU(username, password, email)) {
                // Prints success message.
                System.out.println("Registration successful!");
            } else {
                // Prints Unsuccessful message.
                System.out.println("Unsuccessful!, please try again!");
            }
        } else {
            // Prints unsuccessful message due to incorrect card info
            System.out.println("Invalid card credentials! Please try again.");
        }
    }

    /**
     * Allows a user to log in to their account by providing a username and password.
     * This method lets the user enter their username and password,
     * then checks the stored data to see if the credentials match.
     * If the credentials are valid, the user is welcomed and sent to
     * the login menu. If the credentials are wrong, an error message is displayed.
     */
    public void LoginU() {
        System.out.println("Login to account: ");

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your passwords: ");
        //Reads users password
        String password = scanner.nextLine();
        // looking for the user object from the database usings its username
        // by calling the LoginU method from the userDAO object
        user user = userDAO.LoginU(username);
        // Checks if user was found (user is not null)
        // then checks it the password matches, the password that was stored in user object
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Welcome " + username);
            LogInMenu();
        } else {
            System.out.println("Invalid credentials! Please try again!");
        }
    }

    /**
     * This method is where the user will be sent after login in successfully.
     * The menu will give an option to logout.
     */
    public void LogInMenu() {
        //Reads input from the console
        Scanner scanner = new Scanner(System.in);
        while (loggIn) {
            //Login menu options
            System.out.println("Login Menu");
            System.out.println("1. View All Artists");
            System.out.println("2. View Albums by Artist");
            System.out.println("3. View Songs in an Album");
            System.out.println("4. Search Songs by Title/Artist/Album");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            // User choice
            switch (choice) {
                case 1:
                    viewAllArtists();
                    break;
                case 2:
                    viewAlbumsByArtist();
                    break;
                case 3:
                    viewSongsInAlbum();
                    break;
                case 4:
                    songSearchMenu();
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /**
     * This method displays a message showing that the user has successfully
     * logged out from the system.
     */
    public void logout() {
        //Message displayed when logged out
        System.out.println("You have successfully logged out.");
    }

    private void viewAllArtists() {
        List<Artist> artists = artistDao.getAllArtists();
        System.out.println("Artists in Library:");
        for (Artist artist : artists) {
            System.out.println(artist.getArtistID() + "- " + artist.getArtistName());
        }
    }

    private void viewAlbumsByArtist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id of the artist you would like to view albums of");
        String albumid = sc.nextLine();
        List<Album> albums = albumDao.getAlbumsByArtistId(Integer.parseInt(albumid));
        System.out.println("Artists in Library:");
        for (Album album : albums) {
            System.out.println("- " + album.getAlbumTitle());
        }
    }

    private void viewSongsInAlbum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id of the album you would like to view songs of");
        String songid = sc.nextLine();
        List<Song> songs = albumDao.getSongsForAlbum(Integer.parseInt(songid));
        System.out.println("Songs from album:");
        for (Song song : songs) {
            System.out.println("- " + song.getSongTitle());
        }
    }

    public void songSearchMenu() {
        //Reads input from the console
        Scanner scanner = new Scanner(System.in);

            //Login menu options
            System.out.println("How would you like to search");
            System.out.println("1. By song title");
            System.out.println("2. By artist id");
            System.out.println("3. By Album id");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // User choice
            switch (choice) {
                case 1:
                    getSongsBySongTitle();
                    break;
                case 2:
                    getSongsByArtistId();
                    break;
                case 3:
                    getSongsByAlbumId();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

    }

    private void getSongsByArtistId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id of the artist you would like to view songs of");
        String artistId = sc.nextLine();
        List<Song> songs = songDao.getSongsByArtistId(Integer.parseInt(artistId));
        System.out.println("Songs from your artist:");
        for (Song song : songs) {
            System.out.println("- " + song.getSongTitle());
        }
    }

    private void getSongsByAlbumId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id of the album you would like to view songs of");
        String albumId = sc.nextLine();
        List<Song> songs = songDao.getSongsByAlbumId(Integer.parseInt(albumId));
        System.out.println("Songs from the album:");
        for (Song song : songs) {
            System.out.println("- " + song.getSongTitle());
        }
    }


    private void getSongsBySongTitle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the song(s) you would like to view");
        String songTitle = sc.nextLine();
        List<Song> songs = songDao.getSongsBySongTitle(songTitle);
        System.out.println("Songs in Library with that name:");
        for (Song song : songs) {
            System.out.println("- " + song.getSongTitle());
        }
    }


}
