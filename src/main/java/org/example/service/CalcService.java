package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class CalcService {
    HistoryService historyService;

    public CalcService(HistoryService historyService) {
        this.historyService = historyService;
    }

    private static double mul(double first, double second) {
        return first * second;
    }

    private static double div(double first, double second) {
        return first / second;
    }

    private static double sub(double first, double second) {
        return first - second;
    }

    private static double sum(double first, double second) {
        return first + second;
    }


    public double calculate(String username, double first, double second, String sign) {
        double result;

        switch (sign) {
            case "+" -> {
                result = sum(first, second);
            }
            case "-" -> {
                result = sub(first, second);
            }
            case "/" -> {
                result = div(first, second);
            }
            case "*" -> {
                result = mul(first, second);
            }
            default -> result = 0;
        }

        historyService.addOperation(username, String.format("%.2f %s %.2f = %.2f", first, sign, second, result));
        return result;
    }
}
