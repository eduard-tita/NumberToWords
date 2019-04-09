package ca.et.validation;

/**
 * It validates that number represented as <code>String</code> is a valid number.
 *
 * @author Eduard Tita
 */
public interface NumberValidator {

    /**
     * Validates that number represented as <code>String</code> is a valid number.
     * @param number number to be validated
     * @return <code>true</code> if the number is valid; <code>false</code> otherwise.
     */
    boolean isValidNumber(String number);
}
