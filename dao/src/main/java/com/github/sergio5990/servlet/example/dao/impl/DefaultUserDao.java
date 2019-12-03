package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.converter.UserConverter;
import com.github.sergio5990.servlet.example.dao.entity.UserEntity;
import com.github.sergio5990.servlet.example.dao.repository.UserRepository;
import com.github.sergio5990.servlet.example.model.User;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {
    private final UserRepository repository;

    public DefaultUserDao(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getStudents() {
        final List<UserEntity> entities = repository.findAll();
        return entities.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        repository.save(userEntity);
        return userEntity.getId();
    }
}
