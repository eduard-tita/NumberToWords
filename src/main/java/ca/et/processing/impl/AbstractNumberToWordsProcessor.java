package ca.et.processing.impl;

import java.io.IOException;
import java.io.Writer;

import ca.et.processing.NumberToWordsTransformer;
import ca.et.processing.NumberTooLargeException;
import ca.et.validation.NumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractNumberToWordsProcessor {

    private static Logger logger = LogManager.getLogger();

    private NumberValidator numberValidator;
    private NumberToWordsTransformer numberToWordsTransformer;

    AbstractNumberToWordsProcessor(NumberValidator numberValidator, NumberToWordsTransformer numberToWordsTransformer) {
        this.numberValidator = numberValidator;
        this.numberToWordsTransformer = numberToWordsTransformer;
    }


    String transformNumber(String number, Writer writer) {
        String result;
        if (numberValidator.isValidNumber(number)) {
            try {
                String words = numberToWordsTransformer.transform(number);
                result = number + " : " + words;
                writeMessage(result, writer);
            } catch (NumberTooLargeException e) {
                result = number + " : Number too large";
                writeError(result, e, writer);
            }
        } else {
            result = number + " : Invalid number";
            writeError(result, null, writer);
        }
        return result;
    }

    private void writeMessage(String message, Writer writer) {
        writeToFile(message, writer);
        logger.info(message);
    }

    private void writeError(String message, Exception e, Writer writer) {
        writeToFile(message, writer);
        if (e == null) {
            logger.error(message);
        } else {
            logger.error(message, e);
        }
    }

    private void writeToFile(String message, Writer writer) {
        if (writer != null) {
            try {
                writer.write(message);
                writer.write(System.lineSeparator());
            } catch (IOException e1) {
                logger.error("Cannot write to file; message: '" + message + "'", e1);
            }
        }
    }
}
