package com.sqy.delivery.service.impl;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.user.User;
import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserDto;
import com.sqy.delivery.dto.user.UserSearchRequestDto;
import com.sqy.delivery.repository.UserRepository;
import com.sqy.delivery.service.UserService;
import com.sqy.delivery.service.mapper.UserMapper;
import com.sqy.delivery.service.specification.UserSpecificationBuilder;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceDatabase implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public PagingResponse<UserDto> search(PagingRequest<UserSearchRequestDto> request) {
        log.info("Invoke search({}).", request);
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getLimitOnPage());
        Page<User> packagesPage = userRepository.findAll(
                UserSpecificationBuilder.build(request.getData()),
                pageRequest
        );

        List<UserDto> mappedResult = packagesPage.stream()
                .map(UserMapper::toDto)
                .toList();

        return PagingResponse.<UserDto>newPageBuilder()
                .ok(mappedResult,
                        packagesPage.getTotalElements(),
                        request.getPage(),
                        request.getLimitOnPage());
    }

    @Override
    @Transactional
    public ResponseEntity<UserDto> suspendById(long id) {
        log.info("Invoke suspendById({}).", id);
        return userRepository.findById(id)
                .map(user -> {
                    user.setSuspended(true);
                    userRepository.save(user);
                    return ResponseEntity.ok(UserMapper.toDto(user));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<UserDto> create(UserCreateRequestDto userCreateRequestDto) {
        log.info("Invoke create({}).", userCreateRequestDto);
        ClientDetails userDetails = userCreateRequestDto.userDetails();
        if (!StringUtils.hasText(userDetails.getPhoneNumber())
                || !StringUtils.hasText(userDetails.getFirstName())
                || !StringUtils.hasText(userDetails.getLastName())) {
            return ResponseEntity.badRequest().build();
        }
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(userCreateRequestDto.userDetails().getPhoneNumber());
        if (!matcher.matches()) {
            return ResponseEntity.badRequest().build();
        }
        if (userRepository.existsByCredentials_Login(userCreateRequestDto.credentials().login())) {
            return ResponseEntity.status(409).build();
        }
        User saved = userRepository.save(UserMapper.fromCreateDto(userCreateRequestDto));
        return ResponseEntity.ok(UserMapper.toDto(saved));
    }
}
