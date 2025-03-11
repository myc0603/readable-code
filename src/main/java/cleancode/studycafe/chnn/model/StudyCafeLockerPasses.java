package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerPasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<StudyCafeLockerPass> lockerPasses;

    public StudyCafeLockerPasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void fetchFromFile() {
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public List<StudyCafeLockerPass> getPasses() {
        return new ArrayList<>(lockerPasses);
    }
}
