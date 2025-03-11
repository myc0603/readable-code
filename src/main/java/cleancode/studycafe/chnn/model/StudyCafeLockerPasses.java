package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.List;

public class StudyCafeLockerPasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<StudyCafeLockerPass> lockerPasses;

    public StudyCafeLockerPasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void updatePassesFromFile() {
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public StudyCafeLockerPass getLockerPassCandidate(StudyCafePass studyCafePass) {
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == studyCafePass.getPassType()
                                && option.getDuration() == studyCafePass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }
}
