package d_coding_homepage.user.service;

import d_coding_homepage.auth.JwtProvider;
import d_coding_homepage.auth.token.RefreshToken;
import d_coding_homepage.auth.token.RefreshTokenRepository;
import d_coding_homepage.user.dto.LoginRequestDto;
import d_coding_homepage.user.dto.LoginResponseDto;
import d_coding_homepage.user.dto.SignupRequestDto;
import d_coding_homepage.user.dto.TokenReissueRequestDto;
import d_coding_homepage.user.entity.User;
import d_coding_homepage.user.entity.UserRole;
import d_coding_homepage.user.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public String signup(SignupRequestDto dto){
        Optional<User> userByEmail = userRepository.findUserByEmail(dto.getId());
        if (userByEmail.isPresent()) {
            throw new RuntimeException();   //TODO 이미 존재합니다 exception
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
        log.info("TEST");
        User user = dto.userFromDto();
        UserRole userRole = UserRole.builder()
                .user(user)
                .rolename("ROLE_MEMBER")
                .build();
        user.addRole(userRole);
        userRepository.save(user);

        return user.getEmail();
    }

    public LoginResponseDto login(LoginRequestDto dto){
        User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(); //TODO notfound exception
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String accessToken;
        String refreshToken;
        if (encoder.matches(dto.getPassword(),user.getPassword())){
            accessToken = jwtProvider.createAccessToken(user.getEmail(), getRole(user));
            refreshToken = jwtProvider.createRefreshToken(user.getEmail());

            refreshTokenRepository.save(new RefreshToken(refreshToken, user.getEmail()));
        } else{
            throw new RuntimeException();
        }
        return new LoginResponseDto(accessToken, refreshToken);

    }

    private List<String> getRole(User user) {
        List<String> role = new ArrayList<>();
        List<UserRole> roles = user.getRoles();
        for (UserRole userRole : roles) {
            role.add(userRole.getRolename());
        }

        return role;
    }

    public String reissue(TokenReissueRequestDto dto) {
        String email = jwtProvider.extractEmail(dto.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findById(dto.getRefreshToken()).orElseThrow();
        try {
            if (jwtProvider.validateJwtToken(dto.getRefreshToken()) && email.equals(refreshToken.getMemberId())) {
                User user = userRepository.findUserByEmail(email).orElseThrow();
                List<String> roles = getRole(user);
                return jwtProvider.createAccessToken(email, roles);
            } else {
                throw new RuntimeException(); // token not equal to db
            }
        } catch (JwtException e) {
            throw new RuntimeException(); //invalidToken
        }
    }
}
