package org.example.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {
    private final Scanner sc;
    private final ConsoleWriter writer;

    public ConsoleReader(Scanner sc, ConsoleWriter writer) {
        this.sc = sc;
        this.writer = writer;
    }

    public double getNumber() {
        while (!sc.hasNextInt()) {
            writer.write("Ошибка ввода. Введите число:");
            sc.next();
        }
        return sc.nextDouble();
    }

    public String getSign() {
        while (true) {
            while (!sc.hasNext()) {
                writer.write("Ошибка ввода. Введите знак:");
                sc.next();
            }
            String sign = sc.next();
            if (sign.contains("+") || sign.contains("-") || sign.contains("/") || sign.contains("*")) {
                return sign;
            }
        }
    }

    public String getLine() {
        while (!sc.hasNext()) {
            writer.write("Ошибка ввода. Введите знак:");
            sc.next();
        }
        return sc.next();
    }
}
