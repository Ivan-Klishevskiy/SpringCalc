package org.example.util;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter {
    public void write(String ... txt){
        for (String s : txt) {
            System.out.println(s);
        }
    }

}
