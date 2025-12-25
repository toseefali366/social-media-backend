package com.mecaps.social_media_backend.Security;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        // Try email first
        User user = userRepository.findByEmail(input)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetail(user);
    }
}

