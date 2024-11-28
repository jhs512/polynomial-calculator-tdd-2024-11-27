package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class CalcImpl {
    private String polynomial;

    public int run(String polynomial) {
        this.polynomial = polynomial;
        /*
        1+1 넘길 시  ["1", "+", "1"]list로 저장
         */
        ArrayList<String> subPnList = splitPolynomial();
        ArrayList<Integer> priorityList = getPriorityArr(subPnList);
        int MAX_PRIORITY = 0;
        for (Integer pr : priorityList) {
            if (MAX_PRIORITY < pr) MAX_PRIORITY = pr;
        }

        return calcSplittedPoly(MAX_PRIORITY, priorityList, subPnList);
    }

    public ArrayList<String> splitPolynomial() {
        return new ArrayList<>(Arrays.stream(polynomial.split("[()]"))
                .filter(e -> !e.isEmpty())
                .toList());
    }

    public ArrayList<Integer> getPriorityArr(ArrayList<String> splittedPn) {
        ArrayList<Integer> priorityArr = new ArrayList<>();
        String spn = "(" + polynomial;
        int priority = 0;
        int idx = 0;
        String noparPn = "";
        for (int i = 0; i < spn.length(); i++) {
            if (spn.charAt(i) == '(') {
                priority++;
                noparPn = "";
            } else if (spn.charAt(i) == ')') {
                priority--;
                noparPn = "";
            } else noparPn += spn.charAt(i);

            if (noparPn.equals(splittedPn.get(idx))) {
                priorityArr.add(priority);
                idx++;
            }
            if (idx + 1 > splittedPn.size()) break;
        }
        return priorityArr;
    }

    public int calculate(List<String> sPnList) {
        if (sPnList.size() == 1) return Integer.parseInt(sPnList.getFirst());

        int idx = 1;
        int a = Integer.parseInt(sPnList.get(idx - 1));
        int b = Integer.parseInt(sPnList.get(idx + 1));
        if (sPnList.contains("*")) {
            idx = sPnList.indexOf("*");
            a = Integer.parseInt(sPnList.get(idx - 1));
            b = Integer.parseInt(sPnList.get(idx + 1));
            sPnList.set(idx + 1, (a * b) + "");
            sPnList.remove(idx - 1);
            sPnList.remove(idx - 1);

            return calculate(sPnList);
        } else if (sPnList.contains("/")) {
            idx = sPnList.indexOf("/");
            a = Integer.parseInt(sPnList.get(idx - 1));
            b = Integer.parseInt(sPnList.get(idx + 1));
            sPnList.set(idx + 1, (a / b) + "");
            sPnList.remove(idx - 1);
            sPnList.remove(idx - 1);

            return calculate(sPnList);
        } else if (sPnList.get(idx).equals("+")) {
            sPnList.set(idx + 1, (a + b) + "");
            sPnList.remove(idx - 1);
            sPnList.remove(idx - 1);

            return calculate(sPnList);
        } else if (sPnList.get(idx).equals("-")) {
            sPnList.set(idx + 1, (a - b) + "");
            sPnList.remove(idx - 1);
            sPnList.remove(idx - 1);

            return calculate(sPnList);
        }
        return Integer.parseInt(sPnList.getFirst());
    }

    /**
     *
     * @param max_Priority  현재 괄호 기준 최고 우선순위
     * @param priorities
     * @param polynomials
     * @return
     */
    public int calcSplittedPoly(int max_Priority, ArrayList<Integer> priorities, ArrayList<String> polynomials) {
        int idx = 0;

        if (max_Priority == 0) return Integer.parseInt(polynomials.getFirst());     //우선순위별 계산 완료 시 값 반환

        while (priorities.contains(max_Priority)) {
            idx = priorities.indexOf(max_Priority);
            List<String> splPnList = new ArrayList<>(Arrays.stream(polynomials.get(idx).split(" "))
                    .filter(e -> !e.isEmpty())
                    .toList());
            polynomials.set(idx, calculate(splPnList) + "");
            priorities.set(idx, max_Priority - 1);
        }

        String samePrPoly = polynomials.getFirst();
        int firstPrIdx = 0;
        boolean lastCheck = false;
        for (int i = 1; i < priorities.size(); i++) {
            if (!lastCheck) firstPrIdx = i - 1;
            if (priorities.get(i - 1) == priorities.get(i)) {
                samePrPoly += polynomials.get(i);
                polynomials.set(i, "");
                lastCheck = true;
            } else {
                polynomials.set(firstPrIdx, samePrPoly);
                samePrPoly = polynomials.get(i);
                firstPrIdx = i;
                lastCheck = false;
            }
        }
        polynomials.set(firstPrIdx, samePrPoly);

        ArrayList<Integer> removeIdx = new ArrayList<>();
        for (int i = 0; i < polynomials.size(); i++) {
            if (polynomials.get(i).isEmpty()) removeIdx.add(i);
        }
        for (int ri : removeIdx) {
            priorities.set(ri, -1);
        }
        polynomials.removeIf(String::isEmpty);
        priorities.removeIf(e -> e == -1);

        return calcSplittedPoly(max_Priority - 1, priorities, polynomials);
    }

}
