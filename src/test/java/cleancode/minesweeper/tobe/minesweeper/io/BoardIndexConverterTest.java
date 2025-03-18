package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardIndexConverterTest {

    public BoardIndexConverter converter = new BoardIndexConverter();

    @DisplayName("사용자 입력 문자열로부터 선택 행의 위치를 얻을 수 있다.")
    @Test
    void getSelectedRowIndex() {
        //given
        String userInput = "b8";

        //when
        int selectedRowIndex = converter.getSelectedRowIndex(userInput);

        //then
        assertThat(selectedRowIndex).isEqualTo(7);
    }

    @DisplayName("1보다 작은 숫자를 행의 위치로 입력하면 안 된다.")
    @Test
    void selectWrongRowIndex() {
        //given
        String userInput = "b0";

        //when
        //then
        Assertions.assertThatThrownBy(() -> converter.getSelectedRowIndex(userInput))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");

    }

    @DisplayName("사용자 입력 문자열로부터 선택 열의 위치를 얻을 수 있다.")
    @Test
    void getSelectedCelIndex() {
        //given
        String userInput = "b8";

        //when
        int selectedColIndex = converter.getSelectedColIndex(userInput);

        //then
        assertThat(selectedColIndex).isEqualTo(1);
    }

    // DisplayName 을 너무 추상화하다보니 조금 부정확해지는데 이래도 되나?
    // a 보다 아스키코드상으로 아래에 있는 문자를 열의 위치를 사용할 수 없다고 하면 너무 추상화가 안 된거 같다.
    @DisplayName("열의 위치로는 알파벳 소문자만 사용할 수 있다.")
    @Test
    void selectWrongColIndex() {
        //given
        String userInput = "B8";

        //when

        //then
        assertThatThrownBy(() -> converter.getSelectedColIndex(userInput))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");
    }
}