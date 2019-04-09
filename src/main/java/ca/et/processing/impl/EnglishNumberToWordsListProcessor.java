package ca.et.processing.impl;

import java.util.ArrayList;
import java.util.List;

import ca.et.processing.NumberToWordsListProcessor;
import ca.et.processing.NumberToWordsTransformer;
import ca.et.validation.NumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnglishNumberToWordsListProcessor extends AbstractNumberToWordsProcessor implements NumberToWordsListProcessor {

    private static Logger logger = LogManager.getLogger();

    public EnglishNumberToWordsListProcessor(NumberValidator numberValidator, NumberToWordsTransformer numberToWordsTransformer) {
        super(numberValidator, numberToWordsTransformer);
    }

    @Override
    public List<String> transform(List<String> numbers) {
        logger.info("Transforming program arguments...");
        List<String> list = new ArrayList<>(numbers.size());
        for (String number : numbers) {
            String result = transformNumber(number,  null);
            list.add(result);
        }
        return list;
    }
}
