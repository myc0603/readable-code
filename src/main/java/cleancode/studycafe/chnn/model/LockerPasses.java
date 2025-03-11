package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.List;

public class LockerPasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<LockerPass> enrolledLockerPasses;

    public LockerPasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        enrolledLockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void updatePassesFromFile() {
        enrolledLockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public LockerPass getLockerPassCandidate(StudyCafePass studyCafePass) {
        return enrolledLockerPasses.stream()
                .filter(studyCafePass::isCorrespondingPass)
                .findAny()
                .orElse(LockerPass.UNSELECTED_LOCKER_PASS);
    }
}
