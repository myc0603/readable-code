package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LandMineCellTest {

    @DisplayName("지뢰 셀은 지뢰 셀이다.")
    @Test
    void isLandMine() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when

        //then
        assertThat(landMineCell.isLandMine()).isTrue();
    }

    @DisplayName("지뢰 셀 주변에 지뢰가 없는 건 아니지만 주변 지뢰정보를 저장하지 않는다.")
    @Test
    void hasNearbyLandMineCount() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when

        //then
        assertThat(landMineCell.hasNearbyLandMineCount()).isFalse();
    }

    @DisplayName("열지도 않고 깃발도 안 꽂은 셀은 체크 안된 셀로 표시된다.")
    @Test
    void snapShotOfUncheckedCell() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when

        //then
        assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
    }

    // flag
    @DisplayName("셀에 깃발을 꽂으면 셀의 상태가 깃발 꽂은 상태가 된다.")
    @Test
    void flag() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.flag();

        //then
        assertThat(landMineCell.isFlagged()).isTrue();
    }

    @DisplayName("깃발 꽂은 상태는 오픈 상태가 아니다.")
    @Test
    void flaggedIsNotOpened() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.flag();

        //then
        assertThat(landMineCell.isOpened()).isFalse();
    }

    @DisplayName("지뢰 셀에 깃발 꽂으면 제대로 체크한 것이다.")
    @Test
    void flaggedIsChecked() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.flag();

        //then
        assertThat(landMineCell.isChecked()).isTrue();
    }

    @DisplayName("깃발 꽂은 셀은 깃발로 표시가 된다.")
    @Test
    void snapShotOfFlaggedCell() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.flag();

        //then
        assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
    }

    // open
    @DisplayName("지뢰 셀을 오픈하면 오픈 상태가 된다.")
    @Test
    void open() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.open();

        //then
        assertThat(landMineCell.isOpened()).isTrue();
    }

    @DisplayName("오픈 상태는 깃발 꽂은 상태가 아니다.")
    @Test
    void openedIsNotFlagged() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.open();

        //then
        assertThat(landMineCell.isFlagged()).isFalse();
    }

    @DisplayName("빈 셀을 오픈한거는 제대로 체크한 것이 아니다.")
    @Test
    void openIsChecked() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.open();

        //then
        assertThat(landMineCell.isChecked()).isFalse();
    }

    @DisplayName("오픈한 지뢰 셀은 지뢰로 표시가 된다.")
    @Test
    void snapShotOfOpenedEmptyCell() {
        //given
        LandMineCell landMineCell = new LandMineCell();

        //when
        landMineCell.open();

        //then
        assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofLandMine());
    }
}