package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * Key Press Stoppable Animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String str;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * constructor.
     * @param k key board sensor
     * @param key string that in
     * @param anm the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor k, String key, Animation anm) {
        this.keyboard = k;
        this.str = key;
        this.animation = anm;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.str)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
                return;
            }
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
