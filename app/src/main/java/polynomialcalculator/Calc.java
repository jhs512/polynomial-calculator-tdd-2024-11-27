package polynomialcalculator;

public class Calc {

    public static int plus(int num1, int num2) {;

        return num1 + num2;
    } 

    public static int mult(int num1, int num2) {

        return num1 * num2; 
    }

    public static int min(int num1, int num2) {

        return num1 - num2; 
    }

    public static int div(int num1, int num2) {

        return num1 / num2; 
    }

    public static int run(String cal) {
        String oper = "";
        int ans = 0;
        int left = 0;

        cal = cal.replace("(", "").replace(")", "");

        String[] sArr = cal.split(" ");

        for(String s : sArr) {
            if ( s.equals("+") || 
                 s.equals("-") || 
                 s.equals("*") || 
                 s.equals("/")) {
                oper = s;
            } else {
                left = Integer.parseInt(s);
                if(ans == 0) {
                    ans = left;
                } else if (oper.equals("+")) {
                    ans = plus(ans, Integer.parseInt(s));
                } else if (oper.equals("*")) {
                    ans = mult(ans, Integer.parseInt(s));
                } else if (oper.equals("-")) {
                    ans = min(ans,Integer.parseInt(s));
                } else if (oper.equals("/")) {
                    ans = div(ans,Integer.parseInt(s));
                }
            }
        }

        System.out.println(ans);

        return ans;
    }
}
