package ca.et.processing.impl;

import ca.et.processing.ThreeDigitTransformer;

public class EnglishThreeDigitTransformer implements ThreeDigitTransformer {

    private static final String[] TENS = {
            "", "ten", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] ONES = {
            "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen" };

    @Override
    public String transform(int number) {
        if (number < 1 || number > 999) {
            throw new IllegalArgumentException("Provided argument '" + number + "' is invalid. Expected number range: [1-999].");
        }

        StringBuilder result = new StringBuilder();

        int hundredDigit = number / 100;
        int tensDigits = number % 100;

        if (hundredDigit > 0) {
            result.append(ONES[hundredDigit]).append(" hundred");
            if (tensDigits > 0) {
                result.append(" and ");
            }
        }
        if (tensDigits < 20) {
            result.append(ONES[tensDigits]);
        } else {
            int oneDigit = tensDigits % 10;
            int tenDigit = tensDigits / 10;

            result.append(TENS[tenDigit]);
            if (oneDigit > 0) {
                result.append(" ");
            }
            result.append(ONES[oneDigit]);
        }

        return result.toString();
    }
}
