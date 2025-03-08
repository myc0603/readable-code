package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Level;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        Minesweeper minesweeper = new Minesweeper(gameLevel);
//        Minesweeper minesweeper = new Minesweeper(Level.ADVANCED);
        minesweeper.run();
    }
}
