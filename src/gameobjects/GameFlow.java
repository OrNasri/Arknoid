package gameobjects;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import screens.KeyPressStoppableAnimation;
import screens.GameOver;
import screens.YouWin;

import java.util.List;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 10.06.2021
 * game flow
 */
public class GameFlow {
    public static final int WIN_BONUS = 100;
    private final GUI gui;
    private final AnimationRunner animationRunner;
    private final Counter score;
    private boolean isWin;
    private final KeyboardSensor sensor;

    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Ass6Game", 800, 600);
        this.animationRunner = new AnimationRunner(gui);
        this.score = new Counter();
        this.isWin = true;
        this.sensor = gui.getKeyboardSensor();
    }

    /**
     * make all the levels run one after one.
     * @param levels array of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel gameLevel = new GameLevel(levelInfo, this.gui, this.animationRunner, score);
            gameLevel.initialize();
            while (!gameLevel.shouldStop()) {
                gameLevel.run();
            }
            // check if win or lose
            if (gameLevel.getLife()) {
                this.isWin = false;
                break;
            } else {
                score.increase(WIN_BONUS);
            }
        }
        KeyPressStoppableAnimation end;
        // print the relevant screen
        if (this.isWin) {
            end = new KeyPressStoppableAnimation(this.sensor, "space", new YouWin(score.getValue()));
        } else {
            end = new KeyPressStoppableAnimation(this.sensor, "space", new GameOver(score.getValue()));
        }
        this.animationRunner.run(end);
        gui.close();
    }
}