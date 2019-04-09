package ca.et.processing.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class EnglishThreeDigitTransformerTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    public void transformTest01() {
        logger.info("Enter " + getClass().getSimpleName() + ":transformTest01");
        try {
            EnglishThreeDigitTransformer transformer = new EnglishThreeDigitTransformer();

            assertEquals("five", transformer.transform(5));
            assertEquals("twelve", transformer.transform(12));
            assertEquals("forty", transformer.transform(40));
            assertEquals("fifty two", transformer.transform(52));
            assertEquals("one hundred", transformer.transform(100));
            assertEquals("one hundred and seven", transformer.transform(107));
            assertEquals("three hundred", transformer.transform(300));
            assertEquals("three hundred and fourteen", transformer.transform(314));

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
            EnglishThreeDigitTransformer transformer = new EnglishThreeDigitTransformer();
            transformer.transform(-5);

        } catch (IllegalArgumentException e) {
            logger.info("Expected exception caught: " + e.getMessage());

        } catch (Exception e) {
            logger.error("transformTest02: " + e.getMessage(), e);
            fail("transformTest02: " + e.getMessage());
        } finally {
            logger.info("Exit " + getClass().getSimpleName() + ":transformTest02");
        }
    }
}
