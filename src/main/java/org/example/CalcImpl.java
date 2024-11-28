package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class CalcImpl {
    private String polynomial;

    public int run(String polynomial) {
        this.polynomial = polynomial;

        ArrayList<String> subPnList = splitPolynomial();
        ArrayList<Integer> priorityList = getPriorityArr(subPnList);
        int MAX_PRIORITY = 0;
        for (Integer pr : priorityList) {
            if (MAX_PRIORITY < pr) MAX_PRIORITY = pr;
        }

        return calcSplittedPoly(MAX_PRIORITY, priorityList, subPnList);
    }

    /** 다항식 괄호 별 split 진행 메서드
     * 예시) polynomial = "(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5"
     * @return  ArrayList<String> [2 + 5 * , 3 + 5,  * 5 + -10,  * 10 / 5 ]
     */
    public ArrayList<String> splitPolynomial() {
        return new ArrayList<>(Arrays.stream(polynomial.split("[()]"))
                .filter(e -> !e.isEmpty())
                .toList());
    }

    /**
     * 위의 메서드에서 split한 계산식 별 괄호 depth에 따라 우선도 저장
     * @param splittedPn    예시) [2 + 5 * , 3 + 5,  * 5 + -10,  * 10 / 5 ]
     * @return  ArrayList<Integer> [2, 3, 2, 1]
     */
    public ArrayList<Integer> getPriorityArr(ArrayList<String> splittedPn) {
        ArrayList<Integer> priorityArr = new ArrayList<>();
        String spn = "(" + polynomial;
        int priority = 0;
        int idx = 0;
        String noparPn = "";
        for (int i = 0; i < spn.length(); i++) {
            if (spn.charAt(i) == '(') {
                priority++;     // 여는 괄호 나오면 우선도 +1
                noparPn = "";
            } else if (spn.charAt(i) == ')') {
                priority--;     // 닫는 괄호 나오면 우선도 -1
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

    /**
     * 괄호를 제거한 식에서 숫자, 연산을 split한 list를 param으로 받고 계산
     * @param sPnList    예시) ["10", "*", "20", "+", "10", "+", "5", "*", "2"]
     * 기본 계산법은 해당 연산의 index기준 -1, +1의 위치 숫자 get 하여 계산
     * -> 계산결과를 연산식 3자리에서 1자리로 축소 및 대입  ex) ["10", "*", "20"...] -> ["200"...]
     * -> 대입 후 재귀로 다시 계산
     * "*" || "/"contains? 해당 연산 먼저 계산 : 순서대로 계산
     * @return  ArrayList<Integer> [2, 3, 2, 1]
     */
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
     * 최고 우선 순위의 식 계산 및 우선 순위 -1 -> 모든 계산식의 우선 순위 비교 -> 같은 우선 순위가 연속될 시 해당 계산식 및 우선순위 결합
     * -> step 1 : 우선 순위 : [2, 3, 2, 1]   계산식 : ["2 + 5 * ", "3 + 5", " * 5 + -10", " * 10 / 5" ]
     * -> step 2 :            [2, 2, 2, 1]           ["2 + 5 * ", "8", " * 5 + -10", " * 10 / 5" ]
     * -> step 3 :            [2, 1]                 ["2 + 5 * 8 * 5 + -10", " * 10 / 5" ]
     * 재귀로 호출하여 모든 식 계산
     * @param max_Priority  현재 연산식 기준 최고 우선 순위
     * @param priorities    우선 순위 배열
     * @param polynomials   괄호를 뺀 연산식 배열
     * @return
     */
    public int calcSplittedPoly(int max_Priority, ArrayList<Integer> priorities, ArrayList<String> polynomials) {
        int idx = 0;

        if (max_Priority == 0) return Integer.parseInt(polynomials.getFirst());     //우선순위별 계산 완료 시 값 반환

        // step 1 최고 우선 순위의 식 계산 및 우선 순위 -1
        while (priorities.contains(max_Priority)) {
            idx = priorities.indexOf(max_Priority);
            List<String> splPnList = new ArrayList<>(Arrays.stream(polynomials.get(idx).split(" "))
                    .filter(e -> !e.isEmpty())
                    .toList());
            polynomials.set(idx, calculate(splPnList) + "");
            priorities.set(idx, max_Priority - 1);
        }

        // step 2, 3 모든 계산식의 우선 순위 비교 및 결합
        String samePrPoly = polynomials.getFirst();
        int firstPrIdx = 0;     // 결합의 기준이 되는 index / 해당 index에 결합한 값으로 update
        boolean lastCheck = false;  // 현재 같은 우선순위 결합중인지 여부
        for (int i = 1; i < priorities.size(); i++) {
            if (!lastCheck) firstPrIdx = i - 1;     // 결합중일 경우 기준 index 갱신x

            if (priorities.get(i - 1) == priorities.get(i)) {
                samePrPoly += polynomials.get(i);
                polynomials.set(i, "");
                polynomials.set(firstPrIdx, samePrPoly);
                lastCheck = true;
            } else {
                samePrPoly = polynomials.get(i);
                firstPrIdx = i;
                lastCheck = false;
            }
        }

        // 결합되고 남은 요소 삭제
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
