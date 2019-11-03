package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.HibernateUtil;
import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAuthUserDaoTest {
    private AuthUserDao dao = DefaultAuthUserDao.getInstance();

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

        final AuthUserEntity userEntity = HibernateUtil.getSession().get(AuthUserEntity.class, authUserId);
        assertNotNull(userEntity);
        assertEquals(userEntity.getLogin(), authUser.getLogin());
        assertEquals(userEntity.getPassword(), authUser.getPassword());
    }

}