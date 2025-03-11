package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.List;

public class StudyCafePasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<StudyCafePass> studyCafePasses;

    public StudyCafePasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
    }

    public void updatePassesFromFile() {
        studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
    }

    public List<StudyCafePass> getStudyCafePassCandidates(StudyCafePassType passType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == passType)
                .toList();
    }
}
