package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.HibernateUtil;
import com.github.sergio5990.servlet.example.dao.converter.AuthUserConverter;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.model.AuthUser;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

    @Override
    public AuthUser getByLogin(String login) {
        AuthUserEntity authUser;
        try {
            authUser = (AuthUserEntity) HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("user not found by login{}", login);
            authUser = null;
        }
        return AuthUserConverter.fromEntity(authUser);
    }

    @Override
    public long saveAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(authUserEntity);
        session.getTransaction().commit();
        return authUserEntity.getId();
    }

    @Override
    public void updatePassword(Long authUserId, String newPassword) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update AuthUserEntity set password= :password where id = :authUserId")
                .setParameter("password", newPassword)
                .setParameter("authUserId", authUserId)
                .executeUpdate();
        session.getTransaction().commit();
    }
}
