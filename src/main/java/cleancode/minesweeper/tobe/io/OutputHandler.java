package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;
import cleancode.minesweeper.tobe.GameStatus;

public interface OutputHandler {

    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameEndingComment(GameBoard gameBoard);

    void showCommentForUserAction();

    void showCommentForSelectingCell();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String message);
}
