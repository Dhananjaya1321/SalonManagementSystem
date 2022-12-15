package lk.ijse.salongeetha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidityCheck {


    public static boolean check(Validation validation, String value) {
        Pattern namePattern;
        Matcher matcher;
        switch (validation) {
            case NAME:
                namePattern = Pattern.compile("[a-zA-Z]{2,}");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case NIC:
                namePattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case PASSWORD:
                namePattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case EMAIL:
                namePattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case USERNAME:
                namePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9-_]{3,32}");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case PHONE:
                namePattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9,10}$");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case DATE:
                namePattern = Pattern.compile("[01-12]{2}\\/[01-31]{2}\\/[1-2][0-9]{3}");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case TIME:
                namePattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
        }
        return false;
    }
}
