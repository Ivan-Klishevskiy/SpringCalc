package org.example.service;

import org.example.storage.InMemoryHistoryStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryService {
    InMemoryHistoryStorage inMemoryHistoryStorage;

    public HistoryService(InMemoryHistoryStorage inMemoryHistoryStorage) {
        this.inMemoryHistoryStorage = inMemoryHistoryStorage;
    }

    public void addOperation(String k, String operation){
        inMemoryHistoryStorage.addOperation(k, operation);
    }

    public List<String>getAllHistory(String k){
        return inMemoryHistoryStorage.getAllHistory(k);
    }

    public void deleteHistory(String k) {
        inMemoryHistoryStorage.deleteHistory(k);
    }
}
