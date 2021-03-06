package org.example;

public class CalcService {
    public static double mul(double first, double second){
        return first*second;
    }

    public static double div(double first, double second){
        return first/second;
    }

    public static double sub(double first, double second){
        return first-second;
    }

    public static double sum(double first, double second){
        return  first+second;
    }


    public static double calculate(double first, double second, String sign){
        switch (sign) {
            case "+" -> {
                return sum(first, second);
            }
            case "-"->{
                return sub(first, second);
            }
            case "/"->{
                return div(first, second);
            }
            case "*"->{
                return mul(first, second);
            }
            default ->{
                return 0;
            }
        }
    }
}
