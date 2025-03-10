package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public interface OutputHandler {

    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameEndingComment(GameBoard gameBoard);

    void showCommentForUserAction();

    void showCommentForSelectingCell();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String message);
}
