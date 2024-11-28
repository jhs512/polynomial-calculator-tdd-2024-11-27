package polynomialcalculator.Util;

public class CalcUtil {
    
    public static String RemoveParen(String cal) {
        if(cal.contains("(") && cal.contains(")")) {
            cal = cal.replace("(", "").replace(")", "");
        }

        return cal;
    }
}
