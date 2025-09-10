package org.api;

import org.junit.jupiter.api.Test;
import org.model.User;
import org.service.UserService;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Test
    public void getUserById_shouldReturnOk_whenUserExists() {
        UserService mockService = new UserService();
        UserController controller = new UserController(mockService);
        ResponseEntity<User> response = controller.getUserById("123");
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}


