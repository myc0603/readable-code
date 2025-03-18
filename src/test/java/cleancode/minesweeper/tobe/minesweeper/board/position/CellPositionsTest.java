package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CellPositionsTest {

    CellPositions cellPositions;

    CellPositionsTest() {
        List<CellPosition> positions = new ArrayList<>();
        int rowSize = 8;
        int colSize = 10;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                CellPosition cellPosition = CellPosition.of(row, col);
                positions.add(cellPosition);
            }
        }
        cellPositions = CellPositions.of(positions);
    }

    @DisplayName("중복되지 않게 원하는 랜덤위치를 뽑을 수 있다.")
    @Test
    void extractRandomPositions() {
        //given
        int randomCount = 8;

        //when
        List<CellPosition> randomPositions = cellPositions.extractRandomPositions(randomCount);


        //then
        assertThat(randomPositions).hasSize(randomCount);

        List<CellPosition> distinctRandomPositions = randomPositions.stream()
                .distinct()
                .toList();
        assertThat(distinctRandomPositions).hasSize(randomCount);
    }

    @DisplayName("특정 셀 위치를 제외시킬수 있다.")
    @Test
    void subtract() {
        //given
        List<CellPosition> positionListToSubtract = List.of(
                CellPosition.of(0, 0), CellPosition.of(1, 0), CellPosition.of(0, 1)
        );

        //when
        List<CellPosition> subtractedPositions = cellPositions.subtract(positionListToSubtract);

        //then
        assertThat(subtractedPositions).doesNotContain(
                CellPosition.of(0, 0), CellPosition.of(1, 0), CellPosition.of(0, 1)
        );
    }
}