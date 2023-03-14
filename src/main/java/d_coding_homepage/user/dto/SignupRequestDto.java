package d_coding_homepage.user.dto;

import d_coding_homepage.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignupRequestDto {

    private String id;
    private String password;
    private String name;
    private String phoneNumber;

    public User userFromDto() {
        return new User(id, password, name, phoneNumber);
    }

}
