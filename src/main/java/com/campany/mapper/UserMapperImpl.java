package com.campany.mapper;

import com.campany.dto.PhoneDto;
import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;
import com.campany.model.Phone;
import com.campany.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements  UserMapper {

    @Override
    public User userRequestDtoToUser(UserRequestDto source) {
        return User.builder()
                .name(source.getName())
                .email(source.getEmail())
                .password(source.getPassword())
                .phones(phoneDtoToPhone(source.getPhones()))
                .build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User source) {
        return UserResponseDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .modified(source.getModified())
                .lastLogin(source.getLastLogin())
                .token(source.getToken())
                .isActive(source.isActive())
                .build();
    }

    private List<Phone> phoneDtoToPhone(List<PhoneDto> phoneDtos) {
        return Optional.ofNullable(phoneDtos).stream()
                .flatMap(Collection::stream)
                .map(this::buildPhone)
                .collect(Collectors.toList());
    }

    private Phone buildPhone(PhoneDto phoneDto) {
        return Phone.builder()
                .number(phoneDto.getNumber())
                .citycode(phoneDto.getCitycode())
                .contrycode(phoneDto.getContrycode())
                .build();
    }
}
