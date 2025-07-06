package com.campany.service.impl;

import com.campany.constant.GlobalConstant;
import com.campany.dto.UserRequestDto;
import com.campany.dto.UserResponseDto;
import com.campany.mapper.UserMapper;
import com.campany.model.User;
import com.campany.properties.JwtProperties;
import com.campany.repository.UserRepository;
import com.campany.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProperties jwtProperties;

    private final UserMapper  userMapper;

    @Override
    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {
        User user = userMapper.userRequestDtoToUser(userRequestDto);
        userRepository.findByEmail(user.getEmail()).ifPresent(response -> {
            throw new RuntimeException(GlobalConstant.VALIDATE_EXIST_EMAIL);
        });

        LocalDateTime now = LocalDateTime.now();
        user.setId(UUID.randomUUID());
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setActive(true);
        user.setToken(generateToken(user.getEmail()));
        User save  =  userRepository.save(user);

        return userMapper.userToUserResponseDto(save);
    }

    private String generateToken(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
}
