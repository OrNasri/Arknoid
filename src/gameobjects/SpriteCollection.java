package gameobjects;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 24.04.2021
 */
public class SpriteCollection {
    private final ArrayList<Sprite> spriteList;

    /**
     * Constructor.
     * create sprite collection
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     *
     * @param s add to sprite list
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     *
     * @param s remove from sprite list
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * it's implemented with for loop
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * it's implemented with for loop
     * @param d methods of drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }
}