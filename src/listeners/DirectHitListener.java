package listeners;

import basicobjects.Ball;
import gameobjects.Block;
import interfaces.HitListener;
import levels.One;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * direct hit listener
 */
public class DirectHitListener implements HitListener {
    private final One one;
    /**
     * @param one the level
     */
    public DirectHitListener(One one) {
        this.one = one;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter.getHit()) {
            this.one.setDidIt(true);
        }
    }
}
