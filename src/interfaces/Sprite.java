package interfaces;
import biuoop.DrawSurface;
/**
 * @author Or Nasri
 * @version 2.0
 * @since 30.05.2021
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d methods draw surface
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
