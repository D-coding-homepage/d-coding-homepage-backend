package d_coding_homepage.user.controller;


import d_coding_homepage.auth.JwtProvider;
import d_coding_homepage.user.dto.LoginRequestDto;
import d_coding_homepage.user.dto.LoginResponseDto;
import d_coding_homepage.user.dto.SignupRequestDto;
import d_coding_homepage.user.dto.TokenReissueRequestDto;
import d_coding_homepage.user.repository.UserRepository;
import d_coding_homepage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto dto) {
        log.info("TEST11");
        String id = userService.signup(dto);
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = userService.login(dto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<LoginResponseDto> reissue(@RequestBody TokenReissueRequestDto dto) {
        String newAccessToken = userService.reissue(dto);

        return ResponseEntity.ok().body(new LoginResponseDto(newAccessToken, dto.getRefreshToken()));
    }
}
