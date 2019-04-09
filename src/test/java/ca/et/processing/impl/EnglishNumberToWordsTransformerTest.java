package ca.et.processing.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ca.et.processing.NumberTooLargeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class EnglishNumberToWordsTransformerTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    public void transformTest01() {
        logger.info("Enter " + getClass().getSimpleName() + ":transformTest01");
        try {
            EnglishNumberToWordsTransformer transformer = new EnglishNumberToWordsTransformer(new EnglishThreeDigitTransformer());

            assertEquals("Zero", transformer.transform("0"));
            assertEquals("Five", transformer.transform("5"));
            assertEquals("Twelve", transformer.transform("12"));
            assertEquals("One hundred and twelve", transformer.transform("112"));
            assertEquals("Minus one hundred and twelve", transformer.transform("-112"));
            assertEquals("Five thousand one hundred and twelve", transformer.transform("5,112"));
            assertEquals("One million and thirteen", transformer.transform("1,000,013"));
            assertEquals("Two billion and one", transformer.transform("2,000,000,001"));
            assertEquals("Twenty million two hundred and ten thousand six hundred", transformer.transform("20,210,600"));
            assertEquals("Two hundred and ten million forty thousand and two", transformer.transform("210,040,002"));

            assertEquals("Five quadrillion forty trillion two billion two hundred and ten million forty thousand and two",
                    transformer.transform("5,040,002,210,040,002"));

        } catch (Exception e) {
            logger.error("transformTest01: " + e.getMessage(), e);
            fail("transformTest01: " + e.getMessage());
        } finally {
            logger.info("Exit " + getClass().getSimpleName() + ":transformTest01");
        }
    }


    @Test
    public void transformTest02() {
        logger.info("Enter " + getClass().getSimpleName() + ":transformTest02");
        try {
            EnglishNumberToWordsTransformer transformer = new EnglishNumberToWordsTransformer(new EnglishThreeDigitTransformer());

            try {
                transformer.transform("");
            } catch (IllegalArgumentException e) {
                logger.info("Expected exception caught: " + e.getMessage());
            }

            try {
                transformer.transform("1000000000000000000000000000000000000000000000000000000000000000000000");
            } catch (NumberTooLargeException e) {
                logger.info("Expected exception caught: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.error("transformTest02: " + e.getMessage(), e);
            fail("transformTest02: " + e.getMessage());
        } finally {
            logger.info("Exit " + getClass().getSimpleName() + ":transformTest02");
        }
    }
}
