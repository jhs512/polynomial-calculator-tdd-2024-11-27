package org.example;

import java.util.Optional;

public class Calc {

    public static Integer run(String str) {
        String[] tokens = str.split(" ");
        int[] num = new int[tokens.length];
        String[] operators = new String[tokens.length];

        for(int i = 0; i < tokens.length; i++) {
            if(i % 2 == 0) num[i] = Integer.parseInt(tokens[i]);
            else operators[i] = tokens[i];
        }


        switch (operators[1]) {
            case "+":
                return Optional.of(num[0] + num[2]).get();
            case "-":
                return Optional.of(num[0] - num[2]).get();
            case "*":
                return Optional.of(num[0] * num[2]).get();
            case "/":
                return Optional.of(num[0] / num[2]).get();
        }
        return 0;
    }
}
