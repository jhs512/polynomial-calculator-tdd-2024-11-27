package com.ll;


public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc();
        int run = calc.run("3 * 1 + -3");
        System.out.println(run);
    }
}