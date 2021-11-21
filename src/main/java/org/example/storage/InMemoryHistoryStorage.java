package org.example.storage;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryHistoryStorage {
    private final List<String> historyList;

    public InMemoryHistoryStorage(List<String> historyList) {
        this.historyList = historyList;
    }

    public void addOperation(String operation){
        historyList.add(operation);
    }

    public List<String>getAllHistory(){
        return historyList;
    }
}
