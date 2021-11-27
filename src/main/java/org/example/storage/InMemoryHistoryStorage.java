package org.example.storage;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryHistoryStorage {
    private final Map<String, List<String>> historyList;

    public InMemoryHistoryStorage(Map<String, List<String>> historyList) {
        this.historyList = historyList;
    }

    public void addOperation(String k, String operation){
        List<String> temp = historyList.get(k);
        if(temp==null){
            temp=new ArrayList<>();
            temp.add(operation);
            historyList.put(k,temp);
        }else {
            historyList.get(k).add(operation);
        }
    }

    public List<String>getAllHistory(String k){
        return historyList.get(k);
    }

    public void deleteHistory(String k){
        historyList.remove(k);
    }
}
