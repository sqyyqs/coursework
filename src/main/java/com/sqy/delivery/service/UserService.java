package com.sqy.delivery.service;

import com.sqy.delivery.dto.user.UserCreateRequestDto;
import com.sqy.delivery.dto.user.UserDto;
import com.sqy.delivery.dto.user.UserSearchRequestDto;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    PagingResponse<UserDto> search(PagingRequest<UserSearchRequestDto> userSearchRequestDto);

    ResponseEntity<UserDto> suspendById(long id);

    ResponseEntity<UserDto> create(UserCreateRequestDto userCreateRequestDto);
}
