package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberCellTest {

    @DisplayName("숫자 셀은 지뢰 셀이 아니다.")
    @Test
    void isNotLandMine() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when

        //then
        assertThat(numberCell.isLandMine()).isFalse();
    }

    @DisplayName("숫자 셀 주변에는 지뢰가 있다.")
    @Test
    void hasNearbyLandMineCount() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when

        //then
        assertThat(numberCell.hasNearbyLandMineCount()).isTrue();
    }

    @DisplayName("열지도 않고 깃발도 안 꽂은 셀은 체크 안된 셀로 표시된다.")
    @Test
    void snapShotOfUncheckedCell() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when

        //then
        assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
    }

    // flag
    @DisplayName("숫자 셀에 깃발을 꽂으면 셀의 상태가 깃발 꽂은 상태가 된다.")
    @Test
    void flag() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.flag();

        //then
        assertThat(numberCell.isFlagged()).isTrue();
    }

    @DisplayName("깃발 꽂은 상태는 오픈 상태가 아니다.")
    @Test
    void flaggedIsNotOpened() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.flag();

        //then
        assertThat(numberCell.isOpened()).isFalse();
    }

    @DisplayName("숫자 셀에 깃발 꽂은 건 제대로 체크한게 아니다.")
    @Test
    void flaggedIsNotChecked() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.flag();

        //then
        assertThat(numberCell.isChecked()).isFalse();
    }

    @DisplayName("깃발 꽂은 셀은 깃발로 표시가 된다.")
    @Test
    void snapShotOfFlaggedCell() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.flag();

        //then
        assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
    }

    // open
    @DisplayName("빈 셀을 오픈하면 오픈 상태가 된다.")
    @Test
    void open() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.open();

        //then
        assertThat(numberCell.isOpened()).isTrue();
    }

    @DisplayName("오픈 상태는 깃발 꽂은 상태가 아니다.")
    @Test
    void openedIsNotFlagged() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.open();

        //then
        assertThat(numberCell.isFlagged()).isFalse();
    }

    @DisplayName("빈 셀을 오픈한거는 제대로 체크한 것이다.")
    @Test
    void openIsChecked() {
        //given
        NumberCell numberCell = new NumberCell(1);

        //when
        numberCell.open();

        //then
        assertThat(numberCell.isChecked()).isTrue();
    }

    @DisplayName("오픈한 숫자 셀은 주변 지뢰 숫자 표시가 된다.")
    @Test
    void snapShotOfOpenedEmptyCell() {
        //given
        int nearbyLandMineCount = 1;
        NumberCell numberCell = new NumberCell(nearbyLandMineCount);

        //when
        numberCell.open();

        //then
        assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofNumber(nearbyLandMineCount));
    }
}