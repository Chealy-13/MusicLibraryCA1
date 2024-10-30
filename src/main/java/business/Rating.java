package business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class Rating {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class RatingDAO {
        private int ratingID;
        private int userID;
        private int songID;
        private int rating;
    }
}