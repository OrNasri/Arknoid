package gameobjects;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;
/**
 * @author Or Nasri
 * @version 2.0
 * @since 06.06.2021
 * animation runner.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    /**
     * constructor.
     * @param g gui
     */
    public AnimationRunner(GUI g) {
    this.gui = g;
    this.framesPerSecond = 60;
    this.sleeper = new Sleeper();
    }
    /**
     * run the animation.
     * @param animation to run
     */
    public void run(Animation animation) {
        // timing
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}