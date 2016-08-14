package pentris;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class MathUtils {

    /**
     * Returns whether or not a given point is inside a given range
     *
     * @param bottom the min value
     * @param top the max value
     * @param test the value to check
     * @return if the value to test is in between
     */
    public static boolean isBetween(float bottom, float top, float test) {
        return (test >= bottom && test < top);
    }

    /**
     * Returns whether or not a given value is within a given range of another
     * given value For example, if range is 1 and original is 13, it will return
     * if the value is anywhere between 12 and 14
     *
     * @param range The range to test
     * @param value The value to test
     * @param original The number to compare the value to
     * @return whether or not the value is within a range of the original
     */
    public static boolean inRange(float range, float value, float original) {
        return (value >= original - range) && (value <= original + range);
    }
}
