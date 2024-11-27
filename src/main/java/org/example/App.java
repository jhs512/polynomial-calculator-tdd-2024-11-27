package org.example;

import java.util.Scanner;

public class App {

    public void run() {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        String cmd;

        while(true) {
            System.out.print("Enter expression: ");
            cmd = scanner.nextLine();

            if(cmd.equals("exit")) break;   // 종료 조건

            int calculate = calculator.calculate(cmd);
            System.out.println("calculate = " + calculate);
        }
    }
}
