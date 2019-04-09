package ca.et.validation.impl;

import java.util.regex.Pattern;

import ca.et.validation.NumberValidator;

/**
 * Implementation of the <code>NumberValidator</code> interface that validated integer numbers only.
 *
 * @author Eduard Tita
 */
public class IntegerNumberValidator implements NumberValidator {

    private static final Pattern INTEGER_NUMBER = Pattern.compile("-?[0-9]{1,3}(,?[0-9]{3})*");

    @Override
    public boolean isValidNumber(String numberString) {
        return INTEGER_NUMBER.matcher(numberString).matches();
    }
}
