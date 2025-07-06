package com.campany.mapper;

import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;
import com.campany.model.User;

public interface UserMapper {

    User userRequestDtoToUser(UserRequestDto source);

    UserResponseDto userToUserResponseDto(User source);
}
