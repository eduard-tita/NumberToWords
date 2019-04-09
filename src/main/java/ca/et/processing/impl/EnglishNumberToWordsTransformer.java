package ca.et.processing.impl;

import ca.et.processing.NumberToWordsTransformer;
import ca.et.processing.NumberTooLargeException;
import ca.et.processing.ThreeDigitTransformer;

/**
 * It transforms integer numbers into English word equivalents.<br/>
 * It can handle up to 48 digit long numbers.<br/>
 * Commas are supported as separators of three digit groups.
 *
 * @author Eduard Tita
 */
public class EnglishNumberToWordsTransformer implements NumberToWordsTransformer {

    private static final String[] TRIPLE_POWERS_OF_TEN = {
            "", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion",
            "sextillion", "septillion", "octillion", "nonillion", "decillion" };

    private static final int MAX_LENGTH = 48;

    private ThreeDigitTransformer threeDigitTransformer;

    public EnglishNumberToWordsTransformer(ThreeDigitTransformer threeDigitTransformer) {
        this.threeDigitTransformer = threeDigitTransformer;
    }

    @Override
    public String transform(String numberString) throws NumberTooLargeException {
        if (numberString == null || numberString.length() == 0) {
            throw new IllegalArgumentException("Provided parameter must be a non-empty string");
        }

        String number = removeCommas(numberString);
        if (number.length() > MAX_LENGTH) {
            throw new NumberTooLargeException("The length of the number provided '" + number.length()
                    + "' is larger than the maximum length that can be transformed: " + MAX_LENGTH);
        }

        // Handle zero
        if (number.equals("0")) {
            return "Zero";
        }
        if (number.equals("-0")) {
            return "Minus Zero";
        }

        if (number.startsWith("-")) {
            return "Minus " + transformPositiveNumber(number.substring(1), 0);
        }
        return uppercaseFirstLetter(transformPositiveNumber(number, 0));
    }

    private String transformPositiveNumber(String number, int triplePowerOfTen) {
        String lastThreeDigitNumber = number.length() <= 3 ? number : number.substring(number.length() - 3);

        String partialResult = "";
        if (!lastThreeDigitNumber.equals("000")) {
            partialResult = threeDigitTransformer.transform(Integer.parseInt(lastThreeDigitNumber));
            partialResult += (triplePowerOfTen > 0 ? " " : "") + TRIPLE_POWERS_OF_TEN[triplePowerOfTen];
        }

        if (number.length() <= 3) {
            return partialResult;
        }

        String result = transformPositiveNumber(number.substring(0, number.length()-3), triplePowerOfTen + 1);
        if (lastThreeDigitNumber.equals("000")) {
            return result;
        }
        return result + (lastThreeDigitNumber.startsWith("0") && triplePowerOfTen == 0 ? " and " : " ") + partialResult;
    }

    private String uppercaseFirstLetter(String words) {
        if (words != null && words.length() > 1) {
            return words.substring(0, 1).toUpperCase() + words.substring(1);
        }
        return words;
    }

    private String removeCommas(String numberString) {
        return numberString.replaceAll(",", "");
    }
}
