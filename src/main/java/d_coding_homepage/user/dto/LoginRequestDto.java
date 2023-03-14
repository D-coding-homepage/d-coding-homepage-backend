package d_coding_homepage.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;
}
