package cleancode.studycafe.chnn.io;

import cleancode.studycafe.chnn.model.Pass;
import cleancode.studycafe.chnn.model.StudyCafePassType;

import java.util.Arrays;

public enum Displayer {

    HOULY("%s시간권 - %d원") {
        @Override
        public boolean supports(Pass pass) {
            return pass.getPassType() == StudyCafePassType.HOURLY;
        }
    },
    WEEKLY("%s주권 - %d원") {
        @Override
        public boolean supports(Pass pass) {
            return pass.getPassType() == StudyCafePassType.WEEKLY
                    || pass.getPassType() == StudyCafePassType.FIXED;
        }
    };

    private final String displayFormat;

    Displayer(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public boolean supports(Pass pass) {
        return false;
    }

    public static String display(Pass pass) {
        return Arrays.stream(values())
                .filter(displayer -> displayer.supports(pass))
                .map(displayer -> String.format(displayer.displayFormat, pass.getDuration(), pass.getPrice()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("프로그램 오류입니다."));
    }
}
