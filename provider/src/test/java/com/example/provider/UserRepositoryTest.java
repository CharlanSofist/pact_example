package com.example.provider;

import com.example.provider.model.User;
import com.example.provider.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private List<User> mockUsers;

    @InjectMocks
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User(1, "John", "Doe");
        when(mockUsers.add(user)).thenReturn(true);

        User savedUser = userRepository.saveUser(user);

        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
    }
}
