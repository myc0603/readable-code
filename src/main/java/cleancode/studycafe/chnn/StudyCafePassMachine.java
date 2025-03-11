package cleancode.studycafe.chnn;

import cleancode.studycafe.chnn.exception.AppException;
import cleancode.studycafe.chnn.io.InputHandler;
import cleancode.studycafe.chnn.io.OutputHandler;
import cleancode.studycafe.chnn.model.StudyCafeLockerPass;
import cleancode.studycafe.chnn.model.StudyCafePass;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final PassGetter passGetter = new PassGetter(inputHandler, outputHandler);

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePass studyCafePass = passGetter.getStudyCafePass();
            StudyCafeLockerPass lockerPass = passGetter.getStudyCafeLockerPass(studyCafePass);

            outputHandler.showPassOrderSummary(studyCafePass, lockerPass);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }
}
