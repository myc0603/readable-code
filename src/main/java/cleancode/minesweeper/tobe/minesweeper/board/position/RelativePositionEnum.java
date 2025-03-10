package cleancode.minesweeper.tobe.minesweeper.board.position;

public enum RelativePositionEnum {

    LEFT_UPPER(-1, -1),
    UPPER(-1, 0),
    RIGHT_UPPER(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    LEFT_DOWN(1, -1),
    DOWN(0, -1),
    RIGHT_DOWN(1, 1);

    private final int rowIndex;
    private final int colIndex;

    RelativePositionEnum(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}
