package cleancode.minesweeper.tobe.cell;

public class EmptyCell implements Cell {

    private CellStatus cellStatus = CellStatus.UNCHECKED;

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
        return cellStatus != CellStatus.UNCHECKED;
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
        return false;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if (isOpened()) {
            return CellSnapshot.ofEmpty();
        }

        if (isFlagged()) {
            return CellSnapshot.ofFlag();
        }
        CellStatus unchecked = CellStatus.UNCHECKED;
        return CellSnapshot.ofUnchecked();
    }
}
