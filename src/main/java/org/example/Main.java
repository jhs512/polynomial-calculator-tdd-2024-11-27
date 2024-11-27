package org.example;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc();
        int result = calc.run("((3 + 5) * 5 + -10) * 10 / 5");
        System.out.println("result : " + result);
    }
}