package listeners;
import basicobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;
import gameobjects.GameLevel;
import interfaces.HitListener;

import java.util.List;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 * one block remover
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;
    /**
     * constructor.
     * @param gameLevel the game
     */
    public BlockRemover(GameLevel gameLevel) {
      this.gameLevel = gameLevel;
      this.remainingBlocks = this.gameLevel.getBlocksCounter();
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit block that hit
     * @param hitter the ball that hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
      beingHit.removeFromGame(this.gameLevel);
      // update counter
      this.remainingBlocks.decrease(1);
      List<HitListener> listenerList = beingHit.getHitListeners();
        // remove this listener from the block that is being removed from the game.
      for (int i = 0; i < listenerList.size(); i++) {
          beingHit.removeHitListener(listenerList.get(i));
      }
    }
}
