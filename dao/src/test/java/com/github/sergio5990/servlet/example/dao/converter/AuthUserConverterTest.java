package com.github.sergio5990.servlet.example.dao.converter;

import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthUserConverterTest {

    @Test
    void fromEntityNull() {
        final AuthUser authUser = AuthUserConverter.fromEntity(null);
        assertNull(authUser);
    }

    @Test
    void fromEntityNotNull() {
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setUserId(1L);
        authUserEntity.setRole(Role.STUDENT);
        authUserEntity.setPassword("2");
        authUserEntity.setLogin("log");
        authUserEntity.setUserId(2L);

        final AuthUser authUser = AuthUserConverter.fromEntity(authUserEntity);

        assertNotNull(authUser);
        assertEquals(authUser.getId(), authUserEntity.getId());
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRole(), authUserEntity.getRole());
        assertEquals(authUser.getUserId(), authUserEntity.getUserId());
    }

    @Test
    void toEntityNull() {
        final AuthUserEntity authUser = AuthUserConverter.toEntity(null);
        assertNull(authUser);
    }

    @Test
    void toEntityNotNull() {
        final AuthUser authUser = new AuthUser(1L, "log2", "2", Role.STUDENT, 2L);

        final AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);

        assertNotNull(authUser);
        assertEquals(authUser.getId(), authUserEntity.getId());
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRole(), authUserEntity.getRole());
        assertEquals(authUser.getUserId(), authUserEntity.getUserId());
    }
}