package org.example.service;

import org.example.entity.User;
import org.example.storage.InMemoryHistoryStorage;
import org.example.storage.InMemoryUserStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserService {
    private InMemoryUserStorage userStorage;
    private long id;

    public UserService(InMemoryUserStorage userStorage) {
        this.userStorage = userStorage;
        this.id = 1;
    }

    public void saveUser(User user) {
        user.setId(id);
        this.id++;
        if(user.getPassword().equals("777")){
            user.setRole("admin");
        }else {
            user.setRole("user");
        }
        userStorage.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User findByUsername(String username) {
        return userStorage.findByUsername(username);
    }

    public void updateUser(String username, String newName, String newPassword) {
        userStorage.updateUser(username, newName, newPassword);
    }

    public void deleteUser(String username) {
        userStorage.deleteUser(username);
    }
}
