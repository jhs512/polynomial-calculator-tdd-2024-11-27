package polynomialcalculator.Util;

public class CalcUtil {
    
    public static String evaluate(String cal) {
        while (cal.contains("(")) {
            int open = cal.lastIndexOf("(");
            int close = cal.indexOf(")", open);
            String inner = cal.substring(open, close);
            System.out.println("1");
        }

        return cal;
    }
}
