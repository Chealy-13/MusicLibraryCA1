package business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
