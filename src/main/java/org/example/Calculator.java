package org.example;

public class Calculator {

    public static int run(String expr) {
        // 공백문자 제거
        expr = expr.replaceAll("\\s", "");

        int rs = 0;
        String[] numbers = expr.split("\\+");
        for(int i=0; i<numbers.length; i++){
            rs += Integer.parseInt(numbers[i]);
        }

        return rs;
    }
}
