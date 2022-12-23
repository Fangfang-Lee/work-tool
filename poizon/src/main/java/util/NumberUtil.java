package util;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil {

    public static boolean isNumeric(String str) {
        String english = "^-?\\d*(\\.\\d+)?$";
        if (str.contains( "l" ) || str.contains( "L" ) || !str.matches( english )) {
            return false;
        }
        return NumberUtils.isCreatable( str );
    }

    public static boolean isNumber(String str) {
        String patern = "\\d+(\\.\\d+)?";
        return str.matches(patern);
    }

}
