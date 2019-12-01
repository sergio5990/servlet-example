package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.converter.UserConverter;
import com.github.sergio5990.servlet.example.dao.entity.UserEntity;
import com.github.sergio5990.servlet.example.model.User;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {
    private final SessionFactory factory;

    public DefaultUserDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getStudents() {
        final List<UserEntity> authUser = factory.getCurrentSession()
                .createQuery("from UserEntity")
                .list();
        return authUser.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = factory.getCurrentSession();
        session.save(userEntity);
        return userEntity.getId();
    }
}
