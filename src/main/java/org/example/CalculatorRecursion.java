package org.example;

import org.example.calculator.Calculator;

public class CalculatorRecursion {
    
    Calculator calculator = Calculator.getInstance();

    private final static CalculatorRecursion instance = new CalculatorRecursion();

    private CalculatorRecursion() {}

    public static CalculatorRecursion getInstance() {
        return instance;
    }

//    public int calculate(String expression) {
//        expression = expression.replaceAll("\\s+", "");
//        return calculateRecursion(expression);
//    }

    // 다 갈아 엎을 것

    /**
     *  1. 연산식을 후위 표기법으로 변환
     *  2. 재귀 함수로 연산처리
     *  3. 결과 리턴
     */
//    public int calculateRecursion(String expression) {
//
//    }



}
