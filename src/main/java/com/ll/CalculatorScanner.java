package com.ll;

import java.util.Scanner;

public class CalculatorScanner {
    private CalculatorService calculatorService;
    private CalculatorSplit calculatorSplit;
    private Scanner scanner;
    CalculatorScanner(CalculatorService cse, CalculatorSplit cst){
        calculatorService = cse;
        calculatorSplit = cst;
        scanner = new Scanner(System.in);
    }

    CalculatorScanner(CalculatorService cs,CalculatorSplit cst, Scanner sn){
        calculatorService =cs;
        calculatorSplit = cst;
        scanner = sn;
    }

    double run(){
        return 0;
    }

}
