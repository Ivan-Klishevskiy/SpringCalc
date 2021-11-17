package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {
    private Scanner sc;

    @Autowired
    public ConsoleReader(Scanner sc) {
        this.sc = sc;
    }

    public double getNumber() {
        while (!sc.hasNextInt()) {
            System.out.println("Ошибка ввода. Введите число:");
            sc.next();
        }
        return sc.nextDouble();
    }

    public String getSign() {
        while (true) {
            while (!sc.hasNext()) {
                System.out.println("Ошибка ввода. Введите знак:");
                sc.next();
            }
            String sign= sc.next();
            if(sign.contains("+")||sign.contains("-")||sign.contains("/")||sign.contains("*")){
                return sign;
            }
        }
    }
}
