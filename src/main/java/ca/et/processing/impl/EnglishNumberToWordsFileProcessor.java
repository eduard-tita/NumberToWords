package ca.et.processing.impl;

import java.io.*;

import ca.et.processing.NumberToWordsFileProcessor;
import ca.et.processing.NumberToWordsTransformer;
import ca.et.validation.NumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnglishNumberToWordsFileProcessor extends AbstractNumberToWordsProcessor implements NumberToWordsFileProcessor {

    private static Logger logger = LogManager.getLogger();

    public EnglishNumberToWordsFileProcessor(NumberValidator numberValidator, NumberToWordsTransformer numberToWordsTransformer) {
        super(numberValidator, numberToWordsTransformer);
    }


    @Override
    public void transform(File inputFile, File outputFile) {
        logger.info("Transforming file '" + inputFile.getName() + "' into '" + outputFile.getName() + ".out'...");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String number;
            while ((number = reader.readLine()) != null) {
                transformNumber(number,  writer);
            }
        } catch (FileNotFoundException e) {
            logger.error("Cannot find the provided file: " + inputFile, e);
        } catch (IOException e) {
            logger.error("Cannot perform file processing", e);
        }
    }
}
