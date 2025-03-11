package cleancode.studycafe.chnn;

import cleancode.studycafe.chnn.io.InputHandler;
import cleancode.studycafe.chnn.io.OutputHandler;
import cleancode.studycafe.chnn.io.StudyCafeFileHandler;
import cleancode.studycafe.chnn.model.*;

import java.util.List;

public class PassGetter {

    private static final StudyCafeFileHandler STUDY_CAFE_FILE_HANDLER = new StudyCafeFileHandler();

    private final StudyCafePasses enrolledStudyCafePasses = new StudyCafePasses(STUDY_CAFE_FILE_HANDLER);
    private final LockerPasses enrolledLockerPasses = new LockerPasses(STUDY_CAFE_FILE_HANDLER);
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public PassGetter(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public StudyCafePass getStudyCafePass() {
        StudyCafePassType passType = getStudyCafePassType();
        List<StudyCafePass> passCandidates = enrolledStudyCafePasses.getStudyCafePassCandidates(passType);
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    public LockerPass getStudyCafeLockerPass(StudyCafePass studyCafePass) {
        LockerPass lockerPassCandidate = enrolledLockerPasses.getLockerPassCandidate(studyCafePass);
        if (userDoesNotSelect(lockerPassCandidate)) {
            return null;
        }
        return lockerPassCandidate;
    }

    private StudyCafePassType getStudyCafePassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private boolean userDoesNotSelect(LockerPass lockerPassCandidate) {
        if (exist(lockerPassCandidate)) {
            outputHandler.askLockerPass(lockerPassCandidate);
            return !inputHandler.getLockerSelection();
        }
        return false;
    }

    private boolean exist(LockerPass lockerPassCandidate) {
        return lockerPassCandidate != null;
    }
}
