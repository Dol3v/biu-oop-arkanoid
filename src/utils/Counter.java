package utils;

/**
 * Counts things.
 *
 * @see Integer
 */
public class Counter implements Fetcher<Integer> {

    private int value;

    /**
     * Creates a counter with a given starting value.
     *
     * @param value initial value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases the counter by a given amount.
     *
     * @param number amount to increase
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Decrease the counter by a given amount.
     *
     * @param number amount to decrease
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Retrieves the value of the counter.
     *
     * @return value of counter
     */
    public Integer getValue() {
        return value;
    }
}
