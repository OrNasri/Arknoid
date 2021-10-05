package interfaces;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl that need to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl that need to remove
     */
    void removeHitListener(HitListener hl);
}
