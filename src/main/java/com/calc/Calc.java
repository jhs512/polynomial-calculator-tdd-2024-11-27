package com.calc;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Calc {

	public static int run(String exp) {
		int result = calc(exp);

		return result;
	}

	private static int calc(String exp) {
		boolean plusContains = exp.contains("+");

		if (plusContains) {
			String[] split = exp.split("\\+");

			return Arrays.stream(split).mapToInt(s -> Integer.parseInt(s.trim())).sum();
		}

		return 0;
	}
}
