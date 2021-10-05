package interfaces;

import basicobjects.Ball;
import gameobjects.Block;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that going to be hit
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
