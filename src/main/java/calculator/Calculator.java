package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public static int calculate(String expression) {
        return calculatePoly(expression);
    }

//    private static int calculateWithPar(String expression) {
//        String[] arr = expression.trim().split(" ");
//        //"1 + 3 * 3 - ( 2 * ( 4 + 3 ) - 5 ) - 6 / 2"
//        Stack<String> operatorStk = new Stack<>();
//        Stack<Integer> numberStk = new Stack<>();
//
//
//    }

    private static int calculatePoly(String expression) {
        String[] arr = expression.split(" ");
        List<String> operators = new ArrayList<>();
        List<Integer> operands = new ArrayList<>();

        //"1 + 3 * 3 - 2 + 5 - 6 / 2"
        for (String s : arr) {
            if ("+-/*".contains(s)) {
                operators.add(s);
            } else {
                operands.add(Integer.parseInt(s));
            }
        }

        int res = operands.getFirst();

        int operandIndex = 1;
        int operatorIndex = 0;

        while (operandIndex < operands.size() && operatorIndex < operators.size()) {
            String operator = operators.get(operatorIndex);
            String nextOperator = (operatorIndex+1 < operators.size()) ? operators.get(operatorIndex+1) : null;
            int operand = operands.get(operandIndex);

            //곱 아니면 나누기 일때 먼저 처리한다
            if (nextOperator != null && "*/".contains(nextOperator)) {
                int[] mulAndDivRes = calcMulAndDiv(operators, operands, operatorIndex+1, operandIndex);
                operand = mulAndDivRes[0];
                operatorIndex = mulAndDivRes[1];
                operandIndex = mulAndDivRes[2];
            }

            if (operator.equals("+")) {
                res += operand;
            } else if (operator.equals("-")) {
                res -= operand;
            }

            ++operandIndex;
            ++operatorIndex;
        }

        return res;
    }

    private static int[] calcMulAndDiv(List<String> operators, List<Integer> operands, int operatorIndex, int operandIndex) {
        int res = operands.get(operandIndex);
        ++operandIndex;

        while (operandIndex < operands.size() && operatorIndex < operators.size() && "*/".contains(operators.get(operatorIndex))) {

            String operator = operators.get(operatorIndex);

            if (operator.equals("*")) {
                res *= operands.get(operandIndex);
            } else if (operator.equals("/")) {
                res /= operands.get(operandIndex);
            }

            ++operandIndex;
            ++operatorIndex;
        }

        return new int[]{res, operatorIndex-1, operandIndex-1};
    }

}
