package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class NumberCell implements Cell {

    private final int nearbyLandMineCount;

    private CellStatus cellStatus = CellStatus.UNCHECKED;

    public NumberCell(int nearbyLandMineCount) {
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    @Override
    public void flag() {
        cellStatus = CellStatus.FLAGGED;
    }

    @Override
    public void open() {
        cellStatus = CellStatus.OPENED;
    }

    @Override
    public boolean isChecked() {
        return cellStatus == CellStatus.OPENED;
    }

    @Override
    public boolean isOpened() {
        return cellStatus == CellStatus.OPENED;
    }

    @Override
    public boolean isFlagged() {
        return cellStatus == CellStatus.FLAGGED;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasNearbyLandMineCount() {
        return true;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if (isOpened()) {
            return CellSnapshot.ofNumber(nearbyLandMineCount);
        }

        if (isFlagged()) {
            return CellSnapshot.ofFlag();
        }

        return CellSnapshot.ofUnchecked();
    }
}
