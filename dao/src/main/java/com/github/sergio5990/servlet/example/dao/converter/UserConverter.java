package com.github.sergio5990.servlet.example.dao.converter;

import com.github.sergio5990.servlet.example.dao.entity.UserEntity;
import com.github.sergio5990.servlet.example.model.User;

public class UserConverter {
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    public static User fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail());
    }
}
