package org.example;

public class CalculatorRecursion {
    
    Calculator calculator = Calculator.getInstance();

    private final static CalculatorRecursion instance = new CalculatorRecursion();

    private CalculatorRecursion() {}

    public static CalculatorRecursion getInstance() {
        return instance;
    }


    public int calculateRecursion(String expression) {

        String[] split = expression.split("(?<=[-+*/])|(?=[-+*/])");
        if(split.length == 3) {
            return calculator.calculate(split[1].charAt(0), Integer.parseInt(split[2]), Integer.parseInt(split[0]));
        }

        return -1;
    }

    public int calculate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return calculateRecursion(expression);
    }

}
