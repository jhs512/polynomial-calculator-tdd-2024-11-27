package org.example;

public class CalculatorRecursion {
    
    Calculator calculator = Calculator.getInstance();

    private final static CalculatorRecursion instance = new CalculatorRecursion();

    private CalculatorRecursion() {}

    public static CalculatorRecursion getInstance() {
        return instance;
    }

    public int calculate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return calculateRecursion(expression);
    }

    public int calculateRecursion(String expression) {

        String[] split = expression.split("(?<=[-+*/])|(?=[-+*/])");    // 연산자 앞뒤로 자르기

        if(split.length == 1) {
            return Integer.parseInt(split[0]);
        } else if(split.length == 2 && split[0].equals("-")) {
            return Integer.parseInt(split[1]) * -1;
        }

        int left, right;
        char op;
        if (split[0].equals("-")) {
            left = Integer.parseInt(split[1]) * -1;
            op = split[2].charAt(0);
            if (split[3].equals("-")) {
                right = Integer.parseInt(split[4]) * -1;
            } else right = Integer.parseInt(split[3]);
        } else {
            left = Integer.parseInt(split[0]);
            op = split[1].charAt(0);
            if (split[2].equals("-")) {
                right = Integer.parseInt(split[3]) * -1;
            } else right = Integer.parseInt(split[2]);
        }

        int result = calculator.calculate(op, right, left);

        StringBuilder sb = new StringBuilder();
        sb.append(result);
        if (split[0].equals("-") && split[3].equals("-")) {
            for (int i = 5; i < split.length; i++) {
                sb.append(split[i]);
            }
        } else if(split[0].equals("-") || split[2].equals("-")) {
            for (int i = 4; i < split.length; i++) {
                sb.append(split[i]);
            }
        } else {
            for (int i = 3; i < split.length; i++) {
                sb.append(split[i]);
            }
        }
        return calculateRecursion(sb.toString());
    }

}
