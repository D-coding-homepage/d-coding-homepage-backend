package d_coding_homepage.user.service;

import d_coding_homepage.auth.UserAuthorities;
import d_coding_homepage.user.entity.User;
import d_coding_homepage.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthoritiesService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isBlank()) {
            //TODO implement exception
        }
        User user = userRepository.findUserByEmail(username).orElseThrow();

        return new UserAuthorities(user);
    }
}
