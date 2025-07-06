package com.campany.service;

import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRequestDto userRequestDto);
}
