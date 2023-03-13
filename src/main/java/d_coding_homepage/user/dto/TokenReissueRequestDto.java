package d_coding_homepage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TokenReissueRequestDto {
    private String accessToken;
    private String refreshToken;
}
