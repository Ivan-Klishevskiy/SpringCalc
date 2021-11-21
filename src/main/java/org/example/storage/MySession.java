package org.example.storage;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MySession {

    private static  Map<String,String> session;

    public MySession() {
        session = new HashMap<>();
    }

    public static void setSession(String k, String v){
        session.put(k, v);
    }

    public static String getSession(String k) {
        return session.get(k);
    }

    public static void deleteAttribute(String k){
        session.remove(k);
    }
}
