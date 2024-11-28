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
		boolean plusContains = exp.contains("+");
		boolean minusContains = exp.contains("-");

		if (plusContains) {
			String[] split = exp.split("\\+");

			return Arrays.stream(split).mapToInt(s -> Integer.parseInt(s.trim())).sum();
		}

		if (minusContains) {
			String[] split = exp.split("-");

			int[] num = new int[split.length];

			for(int i = 0; i < split.length; i++) {
				num[i] = Integer.parseInt(split[i].trim());
			}

			return num[0] - num[1];
		}
		return 0;
	}
}
