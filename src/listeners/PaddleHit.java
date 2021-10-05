package listeners;

import basicobjects.Ball;
import gameobjects.Block;
import interfaces.HitListener;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * paddle hit listener
 */
public class PaddleHit implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setHit(true);
    }
}
