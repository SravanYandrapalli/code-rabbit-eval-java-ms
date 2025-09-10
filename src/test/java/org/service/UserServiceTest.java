package org.service;

import org.configuration.AppConfig;
import org.junit.jupiter.api.Test;
import org.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void findUserById_shouldCacheAndReturn() {
        AppConfig cfg = new AppConfig();
        UserService svc = new UserService(cfg);
        User u1 = svc.findUserById("A1");
        User u2 = svc.findUserById("A1");
        assertSame(u1, u2);
        assertEquals("A1", u1.getId());
    }

    @Test
    public void findUserById_blankId_shouldThrow() {
        AppConfig cfg = new AppConfig();
        UserService svc = new UserService(cfg);
        assertThrows(IllegalArgumentException.class, () -> svc.findUserById(" "));
    }
}


