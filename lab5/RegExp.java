package lab5;

import java.util.regex.Pattern;

public class RegExp {

    public static boolean CheckPassport(String passport) {
        Pattern patternPassport = Pattern.compile("(# )\\d+");
        return patternPassport.matcher(passport).find();
    }

    public static boolean CheckData_p(String data_p) {
        Pattern patternData_p = Pattern.compile("^([0-2][0-9]|3[0-1])\\.(0[0-9]|1[0-2])\\.([0-9][0-9])$");
        return patternData_p.matcher(data_p).find();
    }

    public static boolean CheckData_v(String data_v) {
        Pattern patternData_v = Pattern.compile("^([0-2][0-9]|3[0-1])\\.(0[0-9]|1[0-2])\\.([0-9][0-9])$");
        return patternData_v.matcher(data_v).find();
    }


    public static boolean CheckClass1(String class1) {
        Pattern patternReason = Pattern.compile("\\D");
        return patternReason.matcher(class1).find();
    }

    public static boolean CheckPlace1(String place1) {
        Pattern patternPlace1 = Pattern.compile("^([1-6])$");
        return patternPlace1.matcher(place1).find();
    }

    public static boolean CheckReason(String reason) {
        Pattern patternReason = Pattern.compile("\\D");
        return patternReason.matcher(reason).find();
    }
}
