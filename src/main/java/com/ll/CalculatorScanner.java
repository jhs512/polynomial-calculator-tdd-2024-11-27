package com.ll;

import java.util.Scanner;

public class CaculatorScanner {
    private CalculatorService calculatorService;
    private Scanner scanner;
    CaculatorScanner(CalculatorService cse,CalculatorSplit cst){
        calculatorService = cse;
        scanner = new Scanner(System.in);
    }

    CaculatorScanner(CalculatorService cs,Scanner sn){
        calculatorService =cs;
        scanner = sn;
    }

    double run(String str){
        return 0;
    }

}
