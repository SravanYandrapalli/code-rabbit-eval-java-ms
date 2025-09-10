package org.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void equalsAndHashCode_shouldMatchForSameValues() {
        User a = new User("1", "Alice", "alice@example.com");
        User b = new User("1", "Alice", "alice@example.com");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void gettersSetters_shouldWork() {
        User u = new User();
        u.setId("2");
        u.setName("Bob");
        u.setEmail("bob@example.com");
        assertEquals("2", u.getId());
        assertEquals("Bob", u.getName());
        assertEquals("bob@example.com", u.getEmail());
    }
}


