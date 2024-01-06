package com.sqy.delivery.service.impl.auth;

import com.sqy.delivery.repository.AdministratorRepository;
import com.sqy.delivery.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorAuthService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return administratorRepository.findByCredentials_Login(username).map(administrator -> UserPrinciple.builder()
                        .login(administrator.getCredentials().getLogin())
                        .entityId(administrator.getId())
                        .password(administrator.getCredentials().getPassword())
                        .authorities(List.of(new SimpleGrantedAuthority("ADMIN")))
                        .build())
                .orElseThrow();
    }
}
