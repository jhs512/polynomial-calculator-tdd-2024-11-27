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

	/**
	 * 연산 메소드
	 * <p>재귀 방식으로 동작</p>
	 * @param exp
	 */
	private static void calc(String[] exp) {
		int operatorIndex = getOperatorIndex(exp);
		//()가 붙어있는지 저장하는 변수
		boolean hasParentheses = exp[operatorIndex - 1].contains("(");
		// ()랑 -가 붙어있는지 확인
		boolean isNegativePrefix = exp[operatorIndex - 1].contains("-") && hasParentheses;
		// ()가 붙어있다면
		if (hasParentheses) {
			exp[operatorIndex - 1] = exp[operatorIndex - 1].replace("(", "");
			exp[operatorIndex + 1] = exp[operatorIndex + 1].replace(")", "");
		}
		// -가 붙어있다면
		if (isNegativePrefix) {
			exp[operatorIndex - 1] = exp[operatorIndex - 1].replace("-", "");
		}

		//연산자 앞 뒤 숫자 찾아서 연산
		switch (exp[operatorIndex]) {
			case "+":
				int sum = sum(Integer.parseInt(exp[operatorIndex - 1]), Integer.parseInt(exp[operatorIndex + 1]));

				resultInsert(exp, operatorIndex, sum, isNegativePrefix);
				shiftArray(exp, operatorIndex);

				break;
			case "-":
				int minus = minus(Integer.parseInt(exp[operatorIndex - 1]), Integer.parseInt(exp[operatorIndex + 1]));

				resultInsert(exp, operatorIndex, minus, isNegativePrefix);
				shiftArray(exp, operatorIndex);

				break;
			case "*":
				int multiplication = multiplication(Integer.parseInt(exp[operatorIndex - 1]),
					Integer.parseInt(exp[operatorIndex + 1]));

				resultInsert(exp, operatorIndex, multiplication, isNegativePrefix);
				shiftArray(exp, operatorIndex);

				break;

		}

		System.out.print("Calc.calc= ");
		Arrays.stream(expArray).forEach((s) -> System.out.print(s + " "));
		System.out.print("\n");

		if (!exp[1].isBlank()) {
			calc(exp);
		}
	}

	/**
	 * 연산자 인덱스 찾는 메소드
	 * <p>우선순위 : *, / -> +, -</p>
	 * @param exp 연산을 해야하는 배열
	 * @return {@link int} 연산자 인덱스 값
	 */
	private static int getOperatorIndex(String[] exp) {
		int operatorIndex = 0;
		//우선순위 연산자가 붙어있는지 저장하는 변수
		boolean hasPriorityOperator = false;
		//() 인덱스 값 저장하는 변수
		int[] parenthesesIndexes = new int[2];
		//()가 붙어있는지 저정하는 변수
		boolean hasParentheses = false;

		for (int i = 0; i < exp.length; i++) {
			if (exp[i].contains("(")) {
				parenthesesIndexes[0] = i;
				hasParentheses = true;
			}
			if (exp[i].contains(")")) {
				parenthesesIndexes[1] = i;
				break;
			}
		}

		//()가 붙어있을 때
		if (hasParentheses) {
			for (int i = parenthesesIndexes[0]; i < exp.length; i++) {
				if (exp[i].equals("*") || exp[i].equals("/") || exp[i].equals("+") || exp[i].equals("-")) {
					operatorIndex = i;
					break;
				}
			}
		} else {
			// * 가 있는지 찾기
			for (int i = 0; i < exp.length; i++) {
				if (exp[i].equals("*") || exp[i].equals("/")) {
					operatorIndex = i;
				}
			}
			hasPriorityOperator = operatorIndex != 0;

			//우선순위 연산자가 없을 때
			if (!hasPriorityOperator) {
				for (int i = 0; i < exp.length; i++) {
					if (exp[i].equals("+") || exp[i].equals("-")) {
						operatorIndex = i;
						break;
					}
				}
			}
		}
		return operatorIndex;
	}

	/**
	 * 연산 결과를 배열에 추가하는 메소드
	 *
	 * @param exp 연산 결과가 담기지 않은 배열
	 * @param operatorIndex 연산자 인덱스 값
	 * @param result 연산 결과 값
	 * @param isNegativePrefix -기호 앞에 붙었는지 안 붙었는지 여부
	 */
	private static void resultInsert(String[] exp, int operatorIndex, int result, boolean isNegativePrefix) {
		//앞에 -가 붙었을 때
		if (isNegativePrefix) {
			exp[operatorIndex - 1] = "-" + result;
		} else {
			exp[operatorIndex - 1] = String.valueOf(result);
		}
		exp[operatorIndex] = "";
		exp[operatorIndex + 1] = "";
	}

	/**
	 * 더하기 메소드
	 * @param a 왼쪽 값
	 * @param b 오른쪽 값
	 * @return {@link int} 더하기 결과
	 */
	private static int sum(int a, int b) {
		return a + b;
	}

	/**
	 * 빼기 메소드
	 * @param a 왼쪽 값
	 * @param b 오른쪽 값
	 * @return {@link int} 빼기 결과
	 */
	private static int minus(int a, int b) {
		return a - b;
	}

	/**
	 * 곱하기 메소드
	 * @param a 왼쪽 값
	 * @param b 오른쪽 값
	 * @return {@link int} 곱하기 결과
	 */
	private static int multiplication(int a, int b) {
		return a * b;
	}

	/**
	 * 연산이 필요한 값들은 앞쪽으로 재배열하는 메소드
	 * @param stringArray 연산 결과 값이 담긴 배열
	 * @param index 연산자 인덱스 값
	 */
	private static void shiftArray(String[] stringArray, int index) {
		for (int i = index + 2; i < stringArray.length; i++) {
			stringArray[i - 2] = stringArray[i];
			stringArray[i] = "";
		}
	}
}
