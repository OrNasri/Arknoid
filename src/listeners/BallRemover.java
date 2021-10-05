package listeners;
import basicobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;
import gameobjects.GameLevel;
import interfaces.HitListener;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 * one ball remover
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel the game
     * @param removedBalls counter of the ball that removed
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
