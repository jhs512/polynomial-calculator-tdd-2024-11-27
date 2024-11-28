package org.example;

public class CalculatorRecursion {
    
    Calculator calculator = Calculator.getInstance();

    private final static CalculatorRecursion instance = new CalculatorRecursion();

    private CalculatorRecursion() {}

    public static CalculatorRecursion getInstance() {
        return instance;
    }


    public int calculateRecursion(String expression) {

        String[] split = expression.split("(?<=[-+*/])|(?=[-+*/])");    // 연산자 앞뒤로 자르기

        if(split.length == 1) {
            return Integer.parseInt(split[0]);
        }

        int left = Integer.parseInt(split[0]);
        char op = split[1].charAt(0);
        int right = Integer.parseInt(split[2]);

        int result = calculator.calculate(op, right, left);

        StringBuilder sb = new StringBuilder();
        sb.append(result);
        for (int i = 3; i < split.length; i++) {
            sb.append(split[i]);
        }

        return calculateRecursion(sb.toString());
    }

    public int calculate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return calculateRecursion(expression);
    }

}
