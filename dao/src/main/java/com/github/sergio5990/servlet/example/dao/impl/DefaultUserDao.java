package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.HibernateUtil;
import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.converter.AuthUserConverter;
import com.github.sergio5990.servlet.example.dao.converter.UserConverter;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.dao.entity.UserEntity;
import com.github.sergio5990.servlet.example.model.User;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public List<User> getStudents() {
        final List<UserEntity> authUser = HibernateUtil.getSession().createQuery("from UserEntity")
                .list();
        return authUser.stream()
                .map(UserConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        return userEntity.getId();
    }
}
