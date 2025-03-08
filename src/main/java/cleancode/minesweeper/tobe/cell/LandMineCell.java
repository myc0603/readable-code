package cleancode.minesweeper.tobe.cell;

public class LandMineCell implements Cell {

    private static final String LAND_MINE_SIGN = "☼";

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
        return true;
    }

    @Override
    public boolean hasNearbyLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if (isOpened()) {
            return LAND_MINE_SIGN;
        }

        if (isFlagged()) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
