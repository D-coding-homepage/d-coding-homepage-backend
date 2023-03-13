package d_coding_homepage.auth.token;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository  extends CrudRepository<RefreshToken, String> {

    @Override
    Optional<RefreshToken> findById(String memberId);
}
