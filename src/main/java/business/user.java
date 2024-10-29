package business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user in the system with basic details such as user ID, username, password, and email.
 * This class is a Data Transfer Object (DTO) used for handling user data between different parts of the application.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class user
{
    private int userid;
    private String user_name;
    private String password;
    private String email;
}
