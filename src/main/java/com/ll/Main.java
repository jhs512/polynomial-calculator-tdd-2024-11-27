package com.ll;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int result = Calc.run("((15 / (7-(1+1)))*-3) - (2 + (1+1))");
        System.out.println("result = " + result);
    }
}