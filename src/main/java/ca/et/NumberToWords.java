package ca.et;

import java.io.File;
import java.util.Arrays;

import ca.et.processing.NumberToWordsFileProcessor;
import ca.et.processing.NumberToWordsListProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application class. It transforms integer numbers into English word equivalents.
 * Commas are supported as separators of three digit groups.
 *
 * <p><br/>Sample input/output pairs:
 * <dl>
 *     <dt>0</dt><dd>Zero</dd>
 *     <dt>13</dt><dd>Thirteen</dd>
 *     <dt>5,237</dt><dd>Five thousand two hundred and thirty seven</dd>
 * </dl>
 *
 * <p>Usage (1):
 * <p><code>NumberToWords -f path_to_file</code>
 * <p>It transforms all the numbers found in the provided file. It expects one number per line.
 * The results are written into a separate file located in the same directory as the input file.
 * The output file name is created by adding '.out' to toe input file name.
 *
 * <p><br/>Usage (2):
 * <p><code>NumberToWords -n number1_to_be_translated number2_to_be_translated ...</code>
 * <p>It transforms all the numbers given as program arguments after the '-n' argument.
 * The results are logged to the console as well as into 'number-to-words.log'.
 *
 * @author Eduard Tita
 */
public class NumberToWords {

    private static Logger logger = LogManager.getLogger();


    private NumberToWordsFileProcessor fileProcessor;
    private NumberToWordsListProcessor listProcessor;

    private NumberToWords() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        fileProcessor = (NumberToWordsFileProcessor) context.getBean("numberToWordsFileProcessor");
        listProcessor = (NumberToWordsListProcessor) context.getBean("numberToWordsListProcessor");
    }

    private void transform(String[] args) {
        if (args[0].equals("-n")) {
            listProcessor.transform(Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
        } else {
            fileProcessor.transform(new File(args[1]), new File(args[1] + ".out"));
        }
    }


    public static void main(String[] args) {
        if (invalidUsage(args)) {
            printUsage();
            return;
        }

        NumberToWords app = new NumberToWords();
        logger.info("NumberToWords application is up.");

        app.transform(args);

        logger.info("NumberToWords application is shutting down...");
    }

    private static boolean invalidUsage(String[] args) {
        return args.length < 2 || (!args[0].equals("-f") && !args[0].equals("-n"));
    }

    private static void printUsage() {
        logger.error("At least two program arguments are expected.");
        logger.error("Usage:");
        logger.error("\t" + NumberToWords.class.getSimpleName() + " -f <path_to_file>");
        logger.error("\t" + NumberToWords.class.getSimpleName() + " -n <number1_to_be_translated> <number2_to_be_translated> ...");
    }
}
