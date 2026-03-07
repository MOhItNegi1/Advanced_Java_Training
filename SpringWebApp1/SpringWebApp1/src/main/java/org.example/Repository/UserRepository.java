package org.example.Repository;

import org.example.Model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
}
