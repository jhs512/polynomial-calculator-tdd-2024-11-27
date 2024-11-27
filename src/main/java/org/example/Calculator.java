package org.example;

public class Calculator {




    public boolean isValidateFormat(String input) {

        // 숫자랑 */+-() 만 허용
        if (input.matches(".*[^0-9*/+\\-()].*")) {
            throw new IllegalArgumentException("숫자와 */+-() 만 허용합니다.");
        }

        int count = 0;
        for (char c : input.toCharArray()) {
            if(c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
        }
        if(count == 0) {
            return true;
        }
        throw new IllegalArgumentException("괄호를 제대로 입력해주세요.");
    }


}
