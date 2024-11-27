package com.ll;

import com.ll.controller.CalculatorContoller;

import java.util.Scanner;

public class Main {

    private static CalculatorContoller calculatorContoller = new CalculatorContoller();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("계산식 입력: ");
        String s = scanner.nextLine();
        int result = calculatorContoller.run(s);
        System.out.println("결과값: " + result);
    }
}