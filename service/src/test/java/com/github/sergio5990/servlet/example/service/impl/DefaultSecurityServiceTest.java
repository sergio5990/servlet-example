package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultSecurityServiceTest {
    @Mock
    AuthUserDao dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    void testLoginNotExist() {
        when(dao.getByLogin("admin")).thenReturn(null);

        AuthUser login = service.login("admin", "admin");

        assertNull(login);
    }

    @Test
    void testLoginCorrect() {
        when(dao.getByLogin("admin")).thenReturn(new AuthUser(null, "admin", "pass", null, null));

        AuthUser userFromDb = service.login("admin", "pass");

        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "admin");
        assertNotNull(userFromDb.getPassword(), "pass");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getByLogin("admin")).thenReturn(new AuthUser(null, "admin", "pass", null, null));
        AuthUser login = service.login("admin", "pass2");
        assertNull(login);
    }


    @Test
    void update() {
        doNothing().when(dao).updatePassword(any(), any());

        service.updatePassword(5L, "4321");

        verify(dao, times(1)).updatePassword(5L, "4321");
    }
}
