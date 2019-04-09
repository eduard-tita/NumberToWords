package ca.et.processing;

/**
 * Transforms three digit numbers into word equivalents.
 *
 * @author Eduard Tita
 */
public interface ThreeDigitTransformer {

    /**
     * Transforms three digit numbers into word equivalents.
     * @param number integer from 1 to 999 inclusive
     * @return word representation of the given number
     */
    String transform(int number);
}
