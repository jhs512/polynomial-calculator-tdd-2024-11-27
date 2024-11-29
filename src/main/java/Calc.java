import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calc {


    static final int PLUS=1;
    static final int MINUS=2;
    static final int MUL=3;
    static final int DIV=4;
    static final int NONE=0;



    public static int run(String input) {
        int result=0;
        List<Character> poly=convertStringToList(input);
        result=calPoly(poly);

        return result;
    }

    static int calPoly(List<Character> poly) {

        //기저 사례 -> 숫자면 정수로 변환후 리턴
        if(isDigit(poly)) {
            return Integer.parseInt(convertListToString(poly));
        }

        // 처음과 마지막이 괄호로 둘러져있는지 확인
        if(checkPA(poly)) {
            //양수인 경우
            if (poly.get(0) == '(' && poly.getLast() == ')') {
                return calPoly(sliceList(poly, 1, poly.size() - 2));
            }
            // 음수인 경우
            if (poly.get(0) == '-' && poly.get(1) == '(' && poly.get(poly.size() - 1) == ')') {
                return -calPoly(sliceList(poly, 2, poly.size() - 2));
            }
        }

        //우선 순위에 따라 분할 기준이 되는 연산자 탐색
        //우선 순위 낮은 연산자 찾음
        //숫자 부호(-)는 무시함
        //괄호 검출은 스택을 사용해 스택이 빌경우 연산자 탐색에 포함
        Character op='1';
        int op_index=1;
        int bracket_count=0;
        int lowest_priority=NONE;
        for(int i=poly.size()-1;i>=0;i--) {
            Character c=poly.get(i);

            if(c==')') {
                bracket_count+=1;
            } else if(c=='(') {
                bracket_count-=1;
            } else if(bracket_count ==0 && isOp(c)) {
                //괄호가 없는 경우
                if(lowest_priority==NONE || lowest_priority > checkOp(c)) {
                    //저장된 연산자 보다 우선순위가 낮은 경우 + 음수를 나타내는 - 부호가 아닌경우
                    if (c == '-' && (i == 0 || isOp(poly.get(i - 1)))) {
                        continue;
                    }
                    lowest_priority=checkOp(c);
                    op_index=i;
                }



            }
        }
        op=poly.get(op_index);

        //연산자 기준으로 분할 정복
        int a=calPoly(sliceList(poly,0,op_index-1));
        int b=calPoly(sliceList(poly,op_index+1,poly.size()-1));



        if(op=='+') {return a+b;}
        if(op=='-') {return a-b;}
        if(op=='*') {return a*b;}
        if(op=='/') {return a/b;}



        return -1;
    }

    static public boolean checkPA(List<Character> list) {
        int cnt=0;
        //중간에 다시 괄호가 열고 닫히는지 확인 (8+2) * (7-2)
        for(int i=0;i<list.size();i++) {
            if(i==0 && list.get(i)=='-'){ continue;}
            if(list.get(i)=='(') {cnt++;}
            if(list.get(i)==')') {cnt-=1;}
            if(cnt==0 && i !=list.size()-1) {return false;}


        }
        return true;
    }


    static List<Character> convertStringToList (String input) {
        List<Character> list=new ArrayList<>();

        for(int i=0;i<input.length();i++) {
            if(input.charAt(i)!=' ') {
               list.add(input.charAt(i));
            }
        }

        return list;
    }

    static String convertListToString (List<Character> input) {

        StringBuilder sb = new StringBuilder();
        for (char ch : input) {
            sb.append(ch);
        }

        return sb.toString();
    }

    static List<Character> sliceList(List<Character> input,int start,int end) {
        List<Character> list=new ArrayList<>();

        for(int i=start;i<=end;i++) {
            list.add(input.get(i));
        }
        return list;
    }

    static boolean isDigit(List<Character> poly) {

        for (int i=0;i<poly.size();i++) {
            if(i==0 && poly.get(i)=='-'){ continue;}
            if (!(poly.get(i) >=48 && poly.get(i) <=57)) {
                return false;
            }
        }
        return true;
    }

    static int checkOp(Character op) {
        if(op=='+') { return PLUS;}
        if(op=='-') {return MINUS;}
        if(op=='*') {return MUL;}
        if(op=='/') {return DIV;}

        return NONE;
    }


    static boolean isOp(Character op) {
        if(op=='+' || op=='-' || op=='*' || op=='/') {
            return true;
        }
        return false;
    }


    public static void main(String[] args)  {
        Calc.run("-(8 + 2) * -(7 + 3) + 5");
    }


}


