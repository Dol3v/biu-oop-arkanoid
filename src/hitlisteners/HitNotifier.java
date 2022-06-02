package hitlisteners;

/**
 * Notifies listeners when an object was hit.
 *
 * @see HitListener
 */
public interface HitNotifier {

    /**
     * Adds a listener to notify.
     *
     * @param listener listener to add
     */
    void addHitListener(HitListener listener);

    /**
     * Removes a listener to notify.
     *
     * @param listener listener to remove
     */
    void removeHitListener(HitListener listener);
}
