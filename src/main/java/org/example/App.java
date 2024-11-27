package org.example;

import java.util.Scanner;

public class App {

    private final Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        Calculator calculator = Calculator.getInstance();
        String cmd;

        while(true) {
            System.out.print("Enter expression: ");
            cmd = scanner.nextLine();

            if(cmd.equals("exit")) break;   // 종료 조건

            int calculate = calculator.combinedOperations(cmd);
            System.out.println("calculate = " + calculate);
        }
    }
}
