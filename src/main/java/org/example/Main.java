package org.example;

public class Main {
    public static void main(String[] args) {
        CalcImpl calc = new CalcImpl();

        calc.run("(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5 ");
    }
}