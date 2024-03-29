package d_coding_homepage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    String accessToken;
    String refreshToken;
}
