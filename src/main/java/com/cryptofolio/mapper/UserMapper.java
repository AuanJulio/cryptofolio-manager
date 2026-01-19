package com.cryptofolio.mapper;

import com.cryptofolio.dto.request.UserRequest;
import com.cryptofolio.dto.response.UserResponse;
import com.cryptofolio.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(UserRequest request) {
        return User.builder()
                .name(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }

}
