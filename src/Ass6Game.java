import gameobjects.GameFlow;
import interfaces.LevelInformation;
import levels.One;
import levels.FinalFour;
import levels.Three;
import levels.Two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 10.06.2021
 * main of game
 */
public class Ass6Game {
    /**
     * create default array of levels.
     * @return the array
     */
    private static List<LevelInformation> arrayLevels() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(0, new One());
        levels.add(1, new Two());
        levels.add(2, new Three());
        levels.add(3, new FinalFour());
        return levels;
    }


    /**
     * create new game and run it.
     * @param args - the order of the levels in the game.
     */
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        // check if was a invalid input
        boolean flag = true;
        List<LevelInformation> levels = new ArrayList<>();
        GameFlow gameLevel = new GameFlow();
        while (i < args.length) {
            if (args[i].equals("1")) {
                levels.add(j, new One());
                flag = false;
            } else if (args[i].equals("2")) {
                levels.add(j, new Two());
                flag = false;
            } else if (args[i].equals("3")) {
                levels.add(j, new Three());
                flag = false;
            } else if (args[i].equals("4")) {
                levels.add(j, new FinalFour());
                flag = false;
            }
            if (!flag) {
                j++;
            }
            i++;
            flag = true;
        }
        // if there is not input or all the input is invalid
        if (arrayLevels().size() == 0 || j == 0) {
            gameLevel.runLevels(arrayLevels());
            return;
        }
        gameLevel.runLevels(levels);
    }
}