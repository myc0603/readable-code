package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.List;

public class LockerPasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<LockerPass> lockerPasses;

    public LockerPasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void updatePassesFromFile() {
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public LockerPass getLockerPassCandidate(StudyCafePass studyCafePass) {
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == studyCafePass.getPassType()
                                && option.getDuration() == studyCafePass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }
}
