package Persistence;

import lombok.Data;

@Data

public class RatingDAO {
    private int ratingID;
    private int userID;
    private int songID;
    private int rating;

}
