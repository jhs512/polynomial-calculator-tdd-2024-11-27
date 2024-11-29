package com.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Calc {
	private static String[] expArray;

	public static int run(String exp) {
		expArray = exp.split(" ");

		System.out.print("Calc.run= ");
		Arrays.stream(expArray).forEach((s) -> System.out.print(s + " "));
		System.out.print("\n");

		calc(expArray);

		return Integer.parseInt(expArray[0]);
	}

	private static void calc(String[] exp) {
		int operatorIndex = 0;

		for (int i = 0; i < exp.length; i++) {
			exp[i] = exp[i].trim();
			if (exp[i].equals("+") || exp[i].equals("-")) {
				operatorIndex = i;
			}
		}

		switch (exp[operatorIndex]) {
			case "+":
				int sum = sum(Integer.parseInt(exp[operatorIndex - 1]), Integer.parseInt(exp[operatorIndex + 1]));

				resultInsert(exp, operatorIndex, sum);
				shiftArray(exp, operatorIndex);

				break;
			case "-":
				int minus = minus(Integer.parseInt(exp[operatorIndex - 1]), Integer.parseInt(exp[operatorIndex + 1]));

				resultInsert(exp, operatorIndex, minus);

				break;

		}

		System.out.print("Calc.calc= ");
		Arrays.stream(expArray).forEach((s) -> System.out.print(s + " "));

		if (!exp[1].isBlank()) {
			calc(exp);
		}
	}

	private static void resultInsert(String[] exp, int operatorIndex, int sum) {
		exp[operatorIndex - 1] = String.valueOf(sum);
		exp[operatorIndex] = "";
		exp[operatorIndex + 1] = "";
	}

	private static int sum(int a, int b) {
		return a + b;
	}

	private static int minus(int a, int b) {
		return a - b;
	}

	// 배열 축소: 처리된 값을 제거하고 나머지 값을 앞으로 이동
	private static void shiftArray(String[] stringArray, int index) {
		for (int i = index + 2; i < stringArray.length; i++) {
			stringArray[i - 2] = stringArray[i];
		}
		stringArray[index] = "";
		stringArray[index + 1] = "";
	}
}
