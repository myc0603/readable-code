package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.io.ConseleOutputHandler;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;

import java.util.Arrays;
import java.util.Random;

public class Minesweeper {

    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_COL_SIZE = 10;

    private final GameBoard gameBoard = new GameBoard(BOARD_ROW_SIZE, BOARD_COL_SIZE);
    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConseleOutputHandler conseleOutputHandler = new ConseleOutputHandler();
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public void run() {
        conseleOutputHandler.showGameStartComments();
        gameBoard.initializeGame();

        while (true) {
            try {
                conseleOutputHandler.showBoard(gameBoard);

                // 그냥 메서드를 void 로 하고 gameStatus 0 이 아닐때만 실행하도록 하는게 나을거 같다
                boolean end = conseleOutputHandler.printGameEndingComment(gameStatus);
                if (end) {
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                // 파라미터로 예외 e 를 넘길지 e.getMessage()를 넘길지에 대한 고민이 있을 수 있는데,
                // 내 생각에는 (그리고 강사분 생각에는) e 를 넘기는게 나음
                // 만약 e.getMessage()를 넘길거라면 메서드 이름을 예외에 특정할 필요없이 어떤 메시지든 출력해주는 거기 때문에
                // 딱 예외전용으로 메시지를 출력해줄 메서드를 만들었으므로 예외만 던져줘도 메서드 내부에서 알아서 메시지를 만들어서
                // 출력하도록 로직이 짜져있어야되고 그래야 한다고 생각함.
                conseleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                conseleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = getSelectedColIndex(cellInput);
        int selectedRowIndex = getSelectedRowIndex(cellInput);

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changeGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }
        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }

    private int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol);
    }

    private String getUserActionInputFromUser() {
        conseleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        conseleOutputHandler.printCommentForSelectingCell();
        return consoleInputHandler.getUserInput();
    }

    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private int convertRowFrom(char cellInputRow) {
        int rowIndex = Character.getNumericValue(cellInputRow) - 1;
        if (rowIndex >= BOARD_ROW_SIZE) {
            throw new GameException("잘못된 입력입니다.");
        }

        return rowIndex;
    }

    private int convertColFrom(char cellInputCol) {
        switch (cellInputCol) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
                throw new GameException("잘못된 입력입니다.");
        }
    }
}
