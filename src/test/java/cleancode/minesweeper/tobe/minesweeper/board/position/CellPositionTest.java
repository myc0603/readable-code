package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellPositionTest {

    @DisplayName("이동된 셀의 위치를 계산할 수 있는지 확인할 수 있다.")
    @Test
    void canCalculate() {
        //given
        int row = 0;
        int col = 0;
        CellPosition cellPosition = CellPosition.of(row, col);

        int deltaRow = 1;
        int deltaCol = 1;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //when


        //then
        assertThat(cellPosition.canCalculatePositionBy(relativePosition)).isTrue();
    }

    @DisplayName("이동된 셀의 위치의 행의 값이 0보다 작으면 안된다.")
    @Test
    void cannotCalculateWhenRowSmallerThanZero() {
        //given
        int row = 0;
        int col = 0;
        CellPosition cellPosition = CellPosition.of(row, col);

        //when
        int deltaRow = -1;
        int deltaCol = 0;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //then
        assertThat(cellPosition.canCalculatePositionBy(relativePosition)).isFalse();
    }

    @DisplayName("이동된 셀의 위치의 열의 값이 0보다 작으면 안된다.")
    @Test
    void cannotCalculateWhenColSmallerThanZero() {
        //given
        int row = 0;
        int col = 0;
        CellPosition cellPosition = CellPosition.of(row, col);

        //when
        int deltaRow = 1;
        int deltaCol = -1;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //then
        assertThat(cellPosition.canCalculatePositionBy(relativePosition)).isFalse();
    }

    @DisplayName("이동된 셀의 위치를 계산 할 수 있다.")
    @Test
    void calculatePosition() {
        //given
        int row = 0;
        int col = 0;
        CellPosition cellPosition = CellPosition.of(row, col);

        int deltaRow = 2;
        int deltaCol = 3;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //when
        CellPosition newCellPosition = cellPosition.calculatePositionBy(relativePosition);

        //then
        assertThat(newCellPosition.getRowIndex()).isSameAs(row + deltaRow);
        assertThat(newCellPosition.getColIndex()).isSameAs(col + deltaCol);
    }

    @DisplayName("이동된 셀의 행의 위치가 0보다 작으면 안된다.")
    @Test
    void calculatePositionRowEx() {
        //given
        int row = 3;
        int col = 2;
        CellPosition cellPosition = CellPosition.of(row, col);

        //when
        int deltaRow = -4;
        int deltaCol = 3;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

    @DisplayName("이동된 셀의 열의 위치가 0보다 작으면 안된다.")
    @Test
    void calculatePositionColEx() {
        //given
        int row = 3;
        int col = 2;
        CellPosition cellPosition = CellPosition.of(row, col);

        //when
        int deltaRow = 4;
        int deltaCol = -3;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

}