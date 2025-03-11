package cleancode.studycafe.chnn;

import cleancode.studycafe.chnn.io.InputHandler;
import cleancode.studycafe.chnn.io.OutputHandler;
import cleancode.studycafe.chnn.io.StudyCafeFileHandler;
import cleancode.studycafe.chnn.model.*;

import java.util.List;

public class PassGetter {

    private static final StudyCafeFileHandler STUDY_CAFE_FILE_HANDLER = new StudyCafeFileHandler();

    private final StudyCafePasses enrolledStudyCafePasses = new StudyCafePasses(STUDY_CAFE_FILE_HANDLER);
    private final StudyCafeLockerPasses enrolledLockerPasses = new StudyCafeLockerPasses(STUDY_CAFE_FILE_HANDLER);
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public PassGetter(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public StudyCafePass getStudyCafePass() {
        StudyCafePassType passType = getStudyCafePassType();
        List<StudyCafePass> passCandidates = getStudyCafePassCandidates(passType);
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    public StudyCafeLockerPass getStudyCafeLockerPass(StudyCafePass studyCafePass) {
        StudyCafeLockerPass lockerPassCandidate = getLockerPassCandidate(studyCafePass);
        if (userDoesNotSelect(lockerPassCandidate)) {
            return null;
        }
        return lockerPassCandidate;
    }

    private StudyCafePassType getStudyCafePassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private List<StudyCafePass> getStudyCafePassCandidates(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = enrolledStudyCafePasses.getPasses();
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == passType)
                .toList();
    }

    private StudyCafeLockerPass getLockerPassCandidate(StudyCafePass studyCafePass) {
        List<StudyCafeLockerPass> lockerPasses = enrolledLockerPasses.getPasses();
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == studyCafePass.getPassType()
                                && option.getDuration() == studyCafePass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }

    private boolean userDoesNotSelect(StudyCafeLockerPass lockerPassCandidate) {
        if (exist(lockerPassCandidate)) {
            outputHandler.askLockerPass(lockerPassCandidate);
            return !inputHandler.getLockerSelection();
        }
        return false;
    }

    private boolean exist(StudyCafeLockerPass lockerPassCandidate) {
        return lockerPassCandidate != null;
    }
}
