package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.config.DaoConfig;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.Role;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class DefaultAuthUserDaoTest {
    @Autowired
    private AuthUserDao dao;
    @Autowired
    SessionFactory sessionFactory;

    @Test
    void getByLoginExists() {
        dao.saveAuthUser(new AuthUser(null, "Сергей", "1234", Role.STUDENT, null));

        final AuthUser user = dao.getByLogin("Сергей");

        assertNotNull(user);
        assertEquals(user.getLogin(), "Сергей");
    }

    @Test
    void getByLoginNotExist() {
        final AuthUser user = dao.getByLogin("Сергей23");
        assertNull(user);
    }

    @Test
    void saveAuthUser() {
        final AuthUser authUser = new AuthUser(null, "Сергей1", "1234", Role.STUDENT, null);

        final long authUserId = dao.saveAuthUser(authUser);

        final AuthUser userEntity = dao.get(authUserId);
        assertNotNull(userEntity);
        assertEquals(userEntity.getLogin(), authUser.getLogin());
        assertEquals(userEntity.getPassword(), authUser.getPassword());
    }

    @Test
    @Disabled
    void update() {
        final AuthUser authUser = new AuthUser(null, "Сергей3", "1234", Role.STUDENT, null);
        final long authUserId = dao.saveAuthUser(authUser);

        dao.updatePassword(authUserId, "4321");
        sessionFactory.getCurrentSession().flush();

        final AuthUser user = dao.getByLogin("Сергей3");
        assertEquals(user.getPassword(), "4321");
    }
}