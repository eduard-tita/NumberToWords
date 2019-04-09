package ca.et.processing;

/**
 * Transforms integer numbers into word equivalents.
 *
 * @author Eduard Tita
 */

public interface NumberToWordsTransformer {

    /**
     * Transforms integer numbers into word equivalents.
     * @param numberString String representation of the integer to be transformed into words
     * @return word representation of the given number
     * @throws NumberTooLargeException thrown if the parameter exceeds the maximum number that can be transformed into words;
     *         the maximum number is implementation specific
     */
    String transform(String numberString) throws NumberTooLargeException;
}
