package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;

import java.util.Arrays;

public class CellSignFinder {

    private static final CellSignProvider[] PROVIDERS = CellSignProvider.values();

    // 얘를 static 으로 하는게 나을까?
    public String findCellSignFrom(CellSnapshot snapshot) {
        return Arrays.stream(PROVIDERS)
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider -> provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
    }
}
