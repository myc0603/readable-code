package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.*;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.position.RelativePosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameBoard {

    private final Cell[][] board;
    private final int landMineCount;
    private GameStatus gameStatus;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();
        board = new Cell[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
        initializeGameStatus();
    }

    public void initializeGame() {
        initializeGameStatus();
        CellPositions cellPositions = CellPositions.from(board);

        initializeEmptyCells(cellPositions);

        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
        initializeLandMineCells(landMinePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
        initializeNumberCells(numberPositionCandidates);
    }

    public void openAt(CellPosition cellPosition) {
        if (isLandMineCellAt(cellPosition)) {
            openOneCellAt(cellPosition);
            changeGameStatusToLose();
            return;
        }

        openSurroundedCells(cellPosition);
        checkIfGameIsOver();
    }

    public void flagAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.flag();

        checkIfGameIsOver();
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();
        return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
                || cellPosition.isCelIndexMoreThanOrEqual(colSize);
    }

    // by chnn
    private boolean isValidCellPosition(CellPosition cellPosition) {
        return !isInvalidCellPosition(cellPosition);
    }

    public boolean isInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    public boolean isWinStatus() {
        return gameStatus == GameStatus.WIN;
    }

    public boolean isLoseStatus() {
        return gameStatus == GameStatus.LOSE;
    }

    public CellSnapshot getSnapshot(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.getSnapshot();
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    // private
    // initializeGame()
    private void initializeGameStatus() {
        gameStatus = GameStatus.IN_PROGRESS;
    }

    private void initializeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPositions = cellPositions.getPositions();
        for (CellPosition position : allPositions) {
            updateCellAt(position, new EmptyCell());
        }
    }

    private void initializeLandMineCells(List<CellPosition> landMinePositions) {
        for (CellPosition position : landMinePositions) {
            updateCellAt(position, new LandMineCell());
        }
    }

    private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition positionCandidate : numberPositionCandidates) {
            int count = countNearbyLandMines(positionCandidate);
            if (count != 0) {
                updateCellAt(positionCandidate, new NumberCell(count));
            }
        }
    }

    private int countNearbyLandMines(CellPosition cellPosition) {
        return (int) calculateSurroundedPositions(cellPosition)
                .stream()
                .filter(this::isLandMineCellAt)
                .count();
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition) {
        return RelativePosition.SURROUND_POSITIONS.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(this::isValidCellPosition)
                .toList();
    }

    private void updateCellAt(CellPosition position, Cell cell) {
        board[position.getRowIndex()][position.getColIndex()] = cell;
    }

    private void openSurroundedCells(CellPosition cellPosition) {
        Deque<CellPosition> stack = new ArrayDeque<>();
        stack.push(cellPosition);

        while (!stack.isEmpty()) {
            openAndPushCellAt(stack);
        }
    }

    private void openAndPushCellAt(Deque<CellPosition> stack) {
        CellPosition currentCellPosition = stack.pop();
        if (isOpenedCell(currentCellPosition)) {
            return;
        }
        if (isLandMineCellAt(currentCellPosition)) {
            return;
        }

        openOneCellAt(currentCellPosition);

        if (doesCellHaveLandMineCell(currentCellPosition)) {
            return;
        }

        List<CellPosition> surroundedPositions = calculateSurroundedPositions(currentCellPosition);
        surroundedPositions.forEach(stack::push);
    }

    private void openOneCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.open();
    }

    private boolean isOpenedCell(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isOpened();
    }

    private boolean isLandMineCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isLandMine();
    }

    private boolean doesCellHaveLandMineCell(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.hasNearbyLandMineCount();
    }

    private void checkIfGameIsOver() {
        if (isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private boolean isAllCellChecked() {
        Cells cells = Cells.from(board);
        return cells.isAllChecked();
    }

    private void changeGameStatusToWin() {
        gameStatus = GameStatus.WIN;
    }

    private void changeGameStatusToLose() {
        gameStatus = GameStatus.LOSE;
    }

    private Cell findCell(CellPosition cellPosition) {
        return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }
}
