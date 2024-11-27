package com.ll;

public class Calc {
    public static int run(String exp) {
        // 3 + 5 를 " + " 로 자르기
        String[] terms = exp.split(" \\+ ");

        return Integer.parseInt(terms[0]) + Integer.parseInt(terms[1]);
    }
}
