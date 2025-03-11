package cleancode.studycafe.chnn;

import cleancode.studycafe.chnn.exception.AppException;
import cleancode.studycafe.chnn.io.InputHandler;
import cleancode.studycafe.chnn.io.OutputHandler;
import cleancode.studycafe.chnn.io.StudyCafeFileHandler;
import cleancode.studycafe.chnn.model.StudyCafeLockerPass;
import cleancode.studycafe.chnn.model.StudyCafePass;
import cleancode.studycafe.chnn.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> passCandidates = getPassCandidates(studyCafePassType);
            outputHandler.showPassListForSelection(passCandidates);
            StudyCafePass selectedPass = inputHandler.getSelectPass(passCandidates);

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {

                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null); // null 에 해당하는 lockerPass 객체도 만들면 좋을 듯 -> StudyCafeLockerPass 상수로 UNCHECKED_LOCKER_PASS 만들어서 사용

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private static List<StudyCafePass> getPassCandidates(StudyCafePassType passType) {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == passType)
            .toList();
    }

}
