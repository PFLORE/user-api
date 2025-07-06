package com.campany.service.impl;

import com.campany.dto.PhoneDto;
import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;
import com.campany.mapper.UserMapper;
import com.campany.model.User;
import com.campany.properties.JwtProperties;
import com.campany.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwtProperties.getSecret()).thenReturn("SecretKeyForJWTGeneration1234567890");
        when(jwtProperties.getExpiration()).thenReturn(86400000L);
        userService = new UserServiceImpl(userRepository, jwtProperties, userMapper);
    }

    @Test
    void register_ShouldThrowException_WhenEmailExists() {
        UserRequestDto userRequestDto = buildRequestDto();
        User user = buildUser();

        when(userMapper.userRequestDtoToUser(userRequestDto)).thenReturn(user);
        when(userRepository.findByEmail("juan@rodriguez.org")).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(userRequestDto));
        assertEquals("El correo ya registrado", exception.getMessage());
    }

    @Test
    void register_ShouldSaveUser_WhenEmailDoesNotExist() {
        UserRequestDto userRequestDto = buildRequestDto();
        UserResponseDto userResponseDto = buildResponseDto();
        User user = buildUser();

        when(userMapper.userRequestDtoToUser(userRequestDto)).thenReturn(user);
        when(userRepository.findByEmail("juan@rodriguez.org")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.userToUserResponseDto(user)).thenReturn(userResponseDto);

        UserResponseDto savedUser = userService.register(userRequestDto);
        assertNotNull(savedUser);
        assertTrue(savedUser.isActive());
    }

    private UserRequestDto buildRequestDto() {
        PhoneDto phoneDto = PhoneDto.builder()
                .number("1234567")
                .citycode("1")
                .contrycode("57")
                .build();

        return UserRequestDto.builder()
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("Abcdef12")
                .phones(Collections.singletonList(phoneDto))
                .build();
    }

    private UserResponseDto buildResponseDto() {
        LocalDateTime now = LocalDateTime.now();
        return UserResponseDto.builder()
                .id(UUID.randomUUID())
                .created(now)
                .modified(now)
                .lastLogin(now)
                .isActive(true)
                .build();
    }

    private User buildUser() {
        return User.builder()
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("Abcdef12")
                .build();
    }
}