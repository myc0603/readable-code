package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyCellTest {

    @DisplayName("빈 셀은 지뢰 셀이 아니다.")
    @Test
    void isNotLandMine() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when

        //then
        assertThat(emptyCell.isLandMine()).isFalse();
    }

    @DisplayName("빈 셀 주변에는 지뢰가 없다.")
    @Test
    void hasNearbyLandMineCount() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when

        //then
        assertThat(emptyCell.hasNearbyLandMineCount()).isFalse();
    }

    @DisplayName("열지도 않고 깃발도 안 꽂은 셀은 체크 안된 셀로 표시된다.")
    @Test
    void snapShotOfUncheckedCell() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when

        //then
        assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
    }

    // flag
    @DisplayName("빈 셀에 깃발을 꽂으면 셀의 상태가 깃발 꽂은 상태가 된다.")
    @Test
    void flag() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.flag();

        //then
        assertThat(emptyCell.isFlagged()).isTrue();
    }

    @DisplayName("깃발 꽂은 상태는 오픈 상태가 아니다.")
    @Test
    void flaggedIsNotOpened() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.flag();

        //then
        assertThat(emptyCell.isOpened()).isFalse();
    }

    @DisplayName("빈 셀에 깃발 꽂은 건 제대로 체크한게 아니다.")
    @Test
    void flaggedIsNotChecked() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.flag();

        //then
        assertThat(emptyCell.isChecked()).isFalse();
    }

    @DisplayName("깃발 꽂은 셀은 깃발로 표시가 된다.")
    @Test
    void snapShotOfFlaggedCell() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.flag();

        //then
        assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
    }

    // open
    @DisplayName("빈 셀을 오픈하면 오픈 상태가 된다.")
    @Test
    void open() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.open();

        //then
        assertThat(emptyCell.isOpened()).isTrue();
    }

    @DisplayName("오픈 상태는 깃발 꽂은 상태가 아니다.")
    @Test
    void openedIsNotFlagged() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.open();

        //then
        assertThat(emptyCell.isFlagged()).isFalse();
    }

    @DisplayName("빈 셀을 오픈한거는 제대로 체크한 것이다.")
    @Test
    void openIsChecked() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.open();

        //then
        assertThat(emptyCell.isChecked()).isTrue();
    }

    @DisplayName("오픈한 빈 셀은 빈 셀로 표시가 된다.")
    @Test
    void snapShotOfOpenedEmptyCell() {
        //given
        EmptyCell emptyCell = new EmptyCell();

        //when
        emptyCell.open();

        //then
        assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofEmpty());
    }
}