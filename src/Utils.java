/**
 * Utility functions.
 */
public class Utils {

    /**
     * Checks if two doubles are equal up to a rounding error.
     *
     * @param d1 first double
     * @param d2 second double
     * @return if they are equal up to a known error margin
     * @see Consts for the error margin
     */
    public static boolean approximatelyEqual(double d1, double d2) {
        return Math.abs(d1 - d2) <= Consts.ROUNDING_ERROR;
    }
}
