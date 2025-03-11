package cleancode.studycafe.chnn.io;

import cleancode.studycafe.chnn.model.Pass;
import cleancode.studycafe.chnn.model.StudyCafePassType;

public class Displayer {

    private static final String displayFormatForHourly = "%s시간권 - %d원";
    private static final String displayFormatForWeekly = "%s주권 - %d원";

    private Displayer() {
    }

    public static String display(Pass pass) {
        String format = isPassHourly(pass) ? displayFormatForHourly : displayFormatForWeekly;
        return String.format(format, pass.getDuration(), pass.getPrice());
    }

    private static boolean isPassHourly(Pass pass) {
        return pass.getPassType() == StudyCafePassType.HOURLY;
    }
}
