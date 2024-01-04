package com.sqy.delivery.service.impl;

import com.sqy.delivery.domain.user.administratior.Administrator;
import com.sqy.delivery.dto.AdministratorDto;
import com.sqy.delivery.dto.TokenWrapper;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserCredentialsDto;
import com.sqy.delivery.repository.AdministratorRepository;
import com.sqy.delivery.security.JwtIssuer;
import com.sqy.delivery.security.UserPrinciple;
import com.sqy.delivery.service.AdministratorService;
import com.sqy.delivery.service.mapper.AdministratorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdministratorServiceDatabase implements AdministratorService {

    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;

    public AdministratorServiceDatabase(@Qualifier("authenticationManagerAdministrator") AuthenticationManager authenticationManager,
                                        JwtIssuer jwtIssuer, PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
        this.passwordEncoder = passwordEncoder;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public ResponseEntity<TokenWrapper> login(UserCredentialsDto userCredentialsDto) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentialsDto.login(), userCredentialsDto.password())
            );
        } catch (AuthenticationException e) {
            log.error("error: ", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().build();
        }
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        String token = jwtIssuer.issue(
                principal.getEntityId(),
                principal.getUsername(),
                principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );

        return ResponseEntity.ok(new TokenWrapper(token));
    }

    @Override
    public ResponseEntity<AdministratorDto> create(UserCreateRequestDto userCreateRequestDto) {
        log.info("Invoke create({}).", userCreateRequestDto);
        Administrator adminEntityToSave = AdministratorMapper.fromCreateDto(userCreateRequestDto);
        adminEntityToSave.getCredentials().setPassword(passwordEncoder.encode(adminEntityToSave.getCredentials().getPassword()));
        Administrator saved = administratorRepository.save(adminEntityToSave);
        return ResponseEntity.ok(AdministratorMapper.toDto(saved));
    }
}
