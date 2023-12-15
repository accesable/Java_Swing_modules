package org.nhutanh.api.dao;

import org.nhutanh.api.models.User;

import java.util.List;

public interface UserDao {
    User getUserById(int id);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByUsername(String username);
}
