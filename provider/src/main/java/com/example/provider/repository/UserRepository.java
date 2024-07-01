package com.example.provider.repository;


import com.example.provider.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public User saveUser(User user) {
        int id = 32;
        user.setId(id);
        users.add(user);
        return user;
    }
}