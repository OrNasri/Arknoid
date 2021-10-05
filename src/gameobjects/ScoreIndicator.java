package gameobjects;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 * score indicator
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoreCounter;

    /**
     * constructor.
     * @param score initialize the score of the game
     */
    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + scoreCounter.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }

}
