package screens;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;
import interfaces.Animation;
import interfaces.Sprite;

import java.awt.Color;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * count down animation.
 */
public class CountdownAnimation implements Animation {

    private final double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private final SpriteCollection gameScreen;
    private final Sprite background;
    /**
     * constructor.
     * @param numOfSeconds appear on the screen for (numOfSeconds / countFrom) seconds
     * @param countFrom a countdown from countFrom back to 1
     * @param gameScreen the given gameScreen
     * @param background which background use
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.stop = false;
        this.gameScreen = gameScreen;
        this.background = background;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GRAY);
        d.drawText(350, 300, "" + this.countFrom + "", 120);
        if (countFrom != 3) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = ((long) this.numOfSeconds - usedTime);
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom = countFrom - 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
