package cleancode.studycafe.chnn.model;

import cleancode.studycafe.chnn.io.StudyCafeFileHandler;

import java.util.List;

public class StudyCafePasses {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private List<StudyCafePass> enrolledStudyCafePasses;

    public StudyCafePasses(StudyCafeFileHandler studyCafeFileHandler) {
        this.studyCafeFileHandler = studyCafeFileHandler;
        enrolledStudyCafePasses = studyCafeFileHandler.readStudyCafePasses();
    }

    public void updatePassesFromFile() {
        enrolledStudyCafePasses = studyCafeFileHandler.readStudyCafePasses();
    }

    public List<StudyCafePass> getStudyCafePassCandidates(StudyCafePassType passType) {
        return enrolledStudyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSameType(passType))
                .toList();
    }
}
