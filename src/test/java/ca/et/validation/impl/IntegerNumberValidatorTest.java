package ca.et.validation.impl;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class IntegerNumberValidatorTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    public void isValidNumberTest() {
        logger.info("Enter " + getClass().getSimpleName() + ":isValidNumberTest");
        try {
            IntegerNumberValidator numberValidator = new IntegerNumberValidator();

            // positive testing
            assertTrue(numberValidator.isValidNumber("0"));
            assertTrue(numberValidator.isValidNumber("123"));
            assertTrue(numberValidator.isValidNumber("-123"));
            assertTrue(numberValidator.isValidNumber("1,234"));

        } catch (Exception e) {
            logger.error("isValidNumberTest: " + e.getMessage(), e);
            fail("isValidNumberTest: " + e.getMessage());
        } finally {
            logger.info("Exit " + getClass().getSimpleName() + ":isValidNumberTest");
        }
    }
    
    @Test
    public void isValidNumberTest2() {
        logger.info("Enter " + getClass().getSimpleName() + ":isValidNumberTest2");
        try {
            IntegerNumberValidator numberValidator = new IntegerNumberValidator();

            // negative testing
            assertFalse(numberValidator.isValidNumber("x0x"));
            assertFalse(numberValidator.isValidNumber("--1"));
            assertFalse(numberValidator.isValidNumber("12,34"));
            assertFalse(numberValidator.isValidNumber("12,,34"));

        } catch (Exception e) {
            logger.error("isValidNumberTest2: " + e.getMessage(), e);
            fail("isValidNumberTest2: " + e.getMessage());
        } finally {
            logger.info("Exit " + getClass().getSimpleName() + ":isValidNumberTest2");
        }
    }
}
