package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * pause screen.
 */
public class PauseScreen implements Animation {
    private final boolean stop;
    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}
