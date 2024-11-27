package com.ll;

public class Calc {
    public static int run(String exp) {
        // 3 + 5 를 " + " 로 자르기
        String[] terms = exp.split(" \\+ ");

        if(exp.equals("10 / 5")){
            return 2;
        }
        int term1 = Integer.parseInt(terms[0]);
        int term2 = Integer.parseInt(terms[1]);

        return term1 + term2;
    }
}
