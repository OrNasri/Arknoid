package interfaces;

import biuoop.DrawSurface;
/**
 * @author Or Nasri
 * @version 2.0
 * @since 06.06.2021
 * animation.
 */
public interface Animation {
    /**
     * create frame on surface.
     * @param d surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * stop animation.
     * @return true if want to stop
     */
    boolean shouldStop();
}