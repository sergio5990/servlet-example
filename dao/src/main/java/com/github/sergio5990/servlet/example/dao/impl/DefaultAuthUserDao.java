package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.converter.AuthUserConverter;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.model.AuthUser;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private final SessionFactory factory;

    public DefaultAuthUserDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AuthUser getByLogin(String login) {
        AuthUserEntity authUser;
        try {
            authUser = (AuthUserEntity) factory.getCurrentSession()
                    .createQuery("from AuthUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("user not found by login{}", login);
            authUser = null;
        }
        return AuthUserConverter.fromEntity(authUser);
    }

    @Override
    public AuthUser get(Long id) {
        final AuthUserEntity authUserEntity = factory.getCurrentSession().get(AuthUserEntity.class, id);
        return AuthUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public long saveAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        final Session session = factory.getCurrentSession();
        session.save(authUserEntity);
        return authUserEntity.getId();
    }

    @Override
    public void updatePassword(Long authUserId, String newPassword) {
        final Session session = factory.getCurrentSession();
        session.createQuery("update AuthUserEntity set password= :password where id = :authUserId")
                .setParameter("password", newPassword)
                .setParameter("authUserId", authUserId)
                .executeUpdate();
    }
}
