package cleancode.minesweeper.tobe.cell;

public class NumberCell implements Cell {

    private final int nearbyLandMineCount;
//    private final NearbyLandMineCount nearbyLandMineCount;

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
        return true;
    }

    @Override
    public String getSign() {
        if (isOpened()) {
            return String.valueOf(nearbyLandMineCount);
        }

        if (isFlagged()) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
