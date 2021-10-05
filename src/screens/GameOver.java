package screens;

import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * you lose screen
 */
public class GameOver implements Animation {
    private final boolean stop;
    private final int score;

    /**
     * constructor.
     * @param score score the player get until now
     */
    public GameOver(int score) {
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
