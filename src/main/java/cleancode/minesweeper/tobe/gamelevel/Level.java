package cleancode.minesweeper.tobe.gamelevel;

public enum Level {
    ADVANCED(20, 24, 99),
    MIDDLE(14, 18, 40),
    BEGINNER(8, 10, 10),
    VERY_BEGINNER(4, 5, 2);

    private final int rowSize;
    private final int colSize;
    private final int landMineCount;

    Level(int rowSize, int colSize, int landMineCount) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.landMineCount = landMineCount;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public int getLandMineCount() {
        return landMineCount;
    }
}
