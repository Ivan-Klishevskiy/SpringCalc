package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {
    ConsoleReader reader;

    @Autowired
    public Application(ConsoleReader reader) {
        this.reader = reader;
    }

    public void run(){
        System.out.print("First value:");
        double first = reader.getNumber();
        System.out.print("Second value:");
        double second = reader.getNumber();
        String sign = reader.getSign();
        System.out.print("Sign:");
        System.out.println(CalcService.calculate(first,second,sign));
    }
}
