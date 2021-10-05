package gameobjects;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 30.05.2021
 * counter
 */
public class Counter {
    private int counter;
    /**
     * Constructor.
     * create a new counter
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     * @param number to increase the counter
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number to decrease the counter
     */
    public void decrease(int number) {
        this.counter -= number;

    }

    /**
     * get current value of the counter.
     * @return value
     */
    public int getValue() {
        return this.counter;
    }
}
