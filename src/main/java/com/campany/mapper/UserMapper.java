package com.campany.mapper;

import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;
import com.campany.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    @Mapping(target = "name", source = "name")
    User userRequestDtoToUser(UserRequestDto source);

    UserResponseDto userToUserResponseDto(User source);
}
