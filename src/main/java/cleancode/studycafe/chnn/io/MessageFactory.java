package cleancode.studycafe.chnn.io;

import cleancode.studycafe.chnn.model.Pass;
import cleancode.studycafe.chnn.model.StudyCafePassType;
import cleancode.studycafe.chnn.model.TotalPasses;

public class MessageFactory {

    private static final String displayFormatForHourly = "%s시간권 - %d원";
    private static final String displayFormatForWeekly = "%s주권 - %d원";

    private MessageFactory() {
    }

    public static String display(Pass pass) {
        String format = isPassHourly(pass) ? displayFormatForHourly : displayFormatForWeekly;
        return String.format(format, pass.getDuration(), pass.getPrice());
    }

    public static String displayTotalPasses(TotalPasses totalPasses) {
        String output = "이용권: " + MessageFactory.display(totalPasses.getStudyCafePass());
        if (totalPasses.hasLockerPass()) {
            output += "\n사물함: " + MessageFactory.display(totalPasses.getLockerPass());
        }
        return output;
    }

    private static boolean isPassHourly(Pass pass) {
        return pass.getPassType() == StudyCafePassType.HOURLY;
    }
}
