package com.ll;


public class Calc {
    private static final String[] ops = {"+","/"};


    public static int run(String exp) {
        String operator = "";
        String[] terms = null;

        // 입력된 문자열을 연산자 기준으로 분리
        for (String op : ops) {
            if (exp.contains(op.trim())) {
                operator = getOperator(op);
                terms = exp.split(operator);
                break;
            }
        }
        // 연산자를 찾지 못했거나 피연산자가 부족한 경우 예외 처리
        if (terms == null || terms.length != 2) {
            throw new IllegalArgumentException("유효하지 않은 수식입니다: " + exp);
        }

        int term1 = Integer.parseInt(terms[0].trim());
        int term2 = Integer.parseInt(terms[1].trim());

        return switch(operator){
            case "\\+" -> term1 + term2;
            case "/" -> {
                if(term2 == 0){
                    throw new ArithmeticException("0으로 나눌수 없음.");
                }
                yield term1/ term2;
            }
            default -> throw new IllegalStateException("지원하지 않는 연산자 : " + operator);
        };

    }

    private static String getOperator(String op){
        return switch(op){
            case "+" -> "\\+";
            case "/" -> "/";
            default -> throw new IllegalStateException("지원하지 않는 연산자 : " + op);
        };
    }
}
