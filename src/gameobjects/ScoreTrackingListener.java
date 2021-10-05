package gameobjects;

import basicobjects.Ball;
import interfaces.HitListener;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 * score tracking listener
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter update the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * when hit happen.
     * @param beingHit the block that hit
     * @param hitter the ball that hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}