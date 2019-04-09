package ca.et.processing;


/**
 * It is used to indicate that a number cannot be transformed
 * because it's length exceeds the maximum length that the <code>NumberToWordsTransformer</code> can handle.
 *
 * @author Eduard Tita
 */
public class NumberTooLargeException extends Exception {


    public NumberTooLargeException(String message) {
        super(message);
    }

    public NumberTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

}
