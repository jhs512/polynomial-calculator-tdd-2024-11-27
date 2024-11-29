package com.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Calc {

	public static int run(String exp) {
		int result = calc(exp);

		return result;
	}

	private static int calc(String exp) {
		//후위 표기식 변환
		String convertExp = convertExp(exp);
		System.out.println("convertExp = " + convertExp);

		String result = calcResult(convertExp);

		while(result.contains(",")) {
			result = calcResult(result);
		}

		System.out.println("result = " + result);
		return Integer.parseInt(result);
	}

	/**
	 * 최종 연산
	 * <p>후위표기식을 받아 최종 연산하는 메소드 입니다.</p>
	 * @param convertExp
	 * @return {@link String}
	 */
	private static String calcResult(String convertExp) {
		StringBuilder sb = new StringBuilder();
		String[] split = convertExp.split(",");
		System.out.println("split.length = " + split.length);

		//연산자 인덱스 저장용 (첫 번째에 연산자가 올 가능성이 없어 0으로 초기화)
		int operatorIndex = 0;

		//연산자 인덱스 초기화
		for (int i = 0; i < split.length; i++) {
			if (split[i].equals("+") || split[i].equals("-") || split[i].equals("*")) {
				operatorIndex = i;
				break;
			}
		}

		//연산 작업
		switch (split[operatorIndex]) {
			case "+":
				int result = Integer.parseInt(split[operatorIndex - 2]) + Integer.parseInt(split[operatorIndex - 1]);
				sb.append(result + ",");
				break;
			case "-":
				result = Integer.parseInt(split[operatorIndex - 2]) - Integer.parseInt(split[operatorIndex - 1]);
				sb.append(result + ",");
				break;
			case "*":
				result = Integer.parseInt(split[operatorIndex - 2]) * Integer.parseInt(split[operatorIndex - 1]);
				sb.append(result + ",");
				break;
		}

		System.out.println("operatorIndex = " + operatorIndex);
		System.out.println("sb.toString() = " + sb.toString());

		for (int i = operatorIndex+1; i < split.length; i++) {
			if (i == split.length - 1) {
				System.out.println("split = " + split[i]);
				sb.append(split[i]);
				break;
			}
			sb.append(split[i] + ",");
		}
		if (operatorIndex == split.length - 1) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 후위표기식 변환
	 * <p>입력받은 exp를 후위표기식으로 변환하는 메소드</p>
	 * @param exp
	 * @return {@link String}
	 */
	private static String convertExp(String exp) {
		System.out.println("exp = " + exp);
		String[] stringArray = exp.split(" ");

		StringBuilder sb = new StringBuilder();
		int operatorIndex = 0;
		boolean operatorAppend = true;

		//* 연산자 먼저 처리
		for (int i = 0; i < stringArray.length; i++) {
			String s = stringArray[i].trim();
			if (isOperactor(s)) {
				if (s.equals("*")) {
					System.out.println("s = " + s);
					String left = stringArray[i - 1].isBlank() ? "" : stringArray[i - 1] + ",";
					String right = stringArray[i + 1].isBlank() ? "" : stringArray[i + 1] + ",";
					String operator = s + ",";
					sb.append(left + right + operator);
					stringArray[i - 1] = "";
					stringArray[i + 1] = "";
					stringArray[i] = "";
				}
			}
		}

		// +, - 연산자 처리
		for (int i = 0; i < stringArray.length; i++) {
			//10 - 20 + 30
			String s = stringArray[i].trim();
			if (isOperactor(s)) {
				if (s.equals("+") || s.equals("-")) {
					String left = stringArray[i - 1].isBlank() ? "" : stringArray[i - 1] + ",";
					String right = stringArray[i + 1].isBlank() ? "" : stringArray[i + 1] + ",";
					String operator = s + ",";
					sb.append(left + right + operator);
				}
			}

			if (i == stringArray.length - 1) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}

		return sb.toString();
	}

	private static boolean isOperactor(String c) {
		return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
	}
}
