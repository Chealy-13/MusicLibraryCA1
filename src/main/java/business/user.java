package business;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class user
{
    private int user_id;
    private String user_name;
    private String password;
    private String email;
}
