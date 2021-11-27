package org.example.console;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MySession {

    private final Map<Object, String> session;

    public MySession() {
        session = new HashMap<>();
    }

    public void setSession(Object k, String v) {
        session.put(k, v);
    }

    public String getSession(Object k) {
        return session.get(k);
    }

    public void deleteAttribute(Object k) {
        session.remove(k);
    }
}
