package com.sqy.delivery.service.impl.auth;

import com.sqy.delivery.repository.UserRepository;
import com.sqy.delivery.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByCredentials_Login(username)
                .map(user -> UserPrinciple.builder()
                        .login(user.getCredentials().getLogin())
                        .password(user.getCredentials().getPassword())
                        .entityId(user.getId())
                        .authorities(List.of(new SimpleGrantedAuthority("USER")))
                        .build())
                .orElseThrow();
    }
}
