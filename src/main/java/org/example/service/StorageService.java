package org.example.service;

import org.example.entity.User;
import org.example.storage.InMemoryHistoryStorage;
import org.example.storage.InMemoryUserStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StorageService {
    private InMemoryUserStorage userStorage;
    private InMemoryHistoryStorage historyStorage;

    public StorageService(InMemoryUserStorage userStorage, InMemoryHistoryStorage historyStorage) {
        this.userStorage = userStorage;
        this.historyStorage = historyStorage;
    }

    public void saveUser(User user) {
        userStorage.saveUser(user);
    }

    public User findByUsername(String username) {
        return userStorage.findByUsername(username);
    }

    public void updateUser(String username, String newName, String newPassword) {
        userStorage.updateUser(username, newName, newPassword);
    }

    public void deleteUser(String username, String password) {
        userStorage.deleteUser(username,password);
    }

    public void addOperation(double first,String sign,double second,double result){
        historyStorage.addOperation(String.format("%.2f %s %.2f = %.2f", first, sign, second, result));
    }

    public List<String> getAllHistory(){
        return historyStorage.getAllHistory();
    }
}
