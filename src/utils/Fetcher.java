package utils;

/**
 * Allows to fetch a value of type {@code T}. Used because java doesn't have pointers :(
 *
 * @param <T> type of value to fetch
 */
public interface Fetcher<T> {

    /**
     * Fetches the value.
     *
     * @return fetched value
     */
    T getValue();
}
