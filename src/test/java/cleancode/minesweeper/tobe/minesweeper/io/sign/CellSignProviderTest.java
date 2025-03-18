package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellSignProviderTest {

    @DisplayName("아무 체크 안 된 셀의 표시는 □")
    @Test
    void findCellSignFromUncheckedCell() {
        //given
        CellSnapshot uncheckedCellSnapshot = CellSnapshot.ofUnchecked();

        //when
        String uncheckedCellSign = CellSignProvider.findCellSignFrom(uncheckedCellSnapshot);

        //then
        assertThat(uncheckedCellSign).isEqualTo("□");
    }

    @DisplayName("깃발 꽂힌 셀의 표시는 ⚑")
    @Test
    void findCellSignFromFlaggedCell() {
        //given
        CellSnapshot flaggedCellSnapshot = CellSnapshot.ofFlag();

        //when
        String flaggedCellSign = CellSignProvider.findCellSignFrom(flaggedCellSnapshot);

        //then
        assertThat(flaggedCellSign).isEqualTo("⚑");
    }

    @DisplayName("오픈된 빈 셀의 표시는 ■")
    @Test
    void findCellSignFromOpenedEmptyCell() {
        //given
        CellSnapshot emptyCellSnapshot = CellSnapshot.ofEmpty();

        //when
        String emptyCellSign = CellSignProvider.findCellSignFrom(emptyCellSnapshot);

        //then
        assertThat(emptyCellSign).isEqualTo("■");
    }

    @DisplayName("오픈된 숫자 셀의 표시는 숫자")
    @Test
    void findCellSignFromEmptyCell() {
        //given
        int nearbyLandMineCount = 5;
        CellSnapshot numberCellSnapshot = CellSnapshot.ofNumber(nearbyLandMineCount);

        //when
        String numberCellSign = CellSignProvider.findCellSignFrom(numberCellSnapshot);

        //then
        assertThat(numberCellSign).isEqualTo(String.valueOf(nearbyLandMineCount));
    }

    @DisplayName("오픈된 지뢰 셀의 표시는 ☼")
    @Test
    void findCellSignFromLandMineCell() {
        //given
        CellSnapshot landMineSnapshot = CellSnapshot.ofLandMine();

        //when
        String landMineCellSign = CellSignProvider.findCellSignFrom(landMineSnapshot);

        //then
        assertThat(landMineCellSign).isEqualTo("☼");
    }

}