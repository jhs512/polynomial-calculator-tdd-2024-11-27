public class Calc {
    public static int run(String input) {

        //빼기 계산을 음수 더하기로 처리
        input = input.replace("- ", "+ -");

        // while문으로 한번에 한 연산씩 계산
        // if문들로 계산 우선순위 설정
        while (true) {
            if(input.contains("(")){
                input = bracketCal(input); // 괄호 먼저 처리
            }else if (input.contains("*")) {
                input = operation(input, "*"); // 다음 곱하기
            } else if (input.contains("+")) {
                input = operation(input, "+"); // 마지막 더하기
            }else {
                break; // 테스트에 나누기는 없어서 구현 안함
            }
        }

        int result = Integer.parseInt(input); // 결과 숫자로 바꾸기
        return result;
    }

    // 괄호 처리 (가장 안쪽 괄호부터 처리)
    private static String bracketCal(String input) {
        int start = input.lastIndexOf('(');
        int end = input.indexOf(')', start); // 가장 내부 괄호 찾기

        String inside = input.substring(start + 1, end); // 괄호 내부 내용
        String evaluated = String.valueOf(run(inside)); // 다시 run()에 넣어서 계산

        // 괄호 하나만 계산한 결과 반환
        return input.substring(0, start) + evaluated + input.substring(end + 1);
    }

    // 기본 계산 (곱하기, 더하기 가능)
    private static String operation(String input, String ops) {
        String[] parts = input.split(" "); // 숫자와 연산자 분리
        StringBuilder result = new StringBuilder();
        boolean operationDone = false;   // 한번에 하나만 계산되도록

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(ops) && !operationDone) { // 계산 아직 안했으면, 연산자 찾아냄
                int left = Integer.parseInt(parts[i - 1]); // 연산자 왼쪽
                int right = Integer.parseInt(parts[i + 1]); // 연산자 오른쪽

                result.setLength(result.length() - parts[i - 1].length() - 1); // 연산자 왼쪽 지우기
                result.append(parts[i].equals("+") ? left + right : left * right); // 계산 결과 추가
                result.append(" ");
                i++; // 연산자 오른쪽 건너뛰기
                operationDone = true; // 계산 한번씩만 하기 위한 확인
            } else {
                result.append(parts[i]).append(" "); // 나머지 결과 그대로 반환
            }
        }
        return result.toString().strip(); // 결과 다시 String으로 반환
    }
}