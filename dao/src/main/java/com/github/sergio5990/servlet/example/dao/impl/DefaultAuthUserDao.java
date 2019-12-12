package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.converter.AuthUserConverter;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.dao.repository.AuthUserRepository;
import com.github.sergio5990.servlet.example.model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private final AuthUserRepository repository;

    public DefaultAuthUserDao(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthUser getByLogin(String login) {
        return repository.findByLogin(login)
                .map(AuthUserConverter::fromEntity)
                .orElse(null);
    }

    @Override
    public AuthUser get(Long id) {
        final AuthUserEntity authUserEntity = repository.getOne(id);
        return AuthUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public long saveAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        repository.save(authUserEntity);
        return authUserEntity.getId();
    }

    @Override
    public void updatePassword(Long authUserId, String newPassword) {
        repository.updatePassword(authUserId, newPassword);
    }
}
