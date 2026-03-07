package org.example.Service;

import org.example.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
}
