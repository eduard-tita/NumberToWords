package ca.et.processing;

import java.io.File;

/**
 * It transforms to English equivalents all the numbers found in the provided input file.
 * It expects one number per line.
 * The results are written into the output file.
 *
 * @author Eduard Tita
 */
public interface NumberToWordsFileProcessor {

    /**
     * It transforms to English equivalents all the numbers found in the provided input file.
     * @param inputFile contains the numbers to be transformed; it expects one number per line.
     * @param outputFile contains the transformation results; one output line corresponds to one input number.
     */
    void transform(File inputFile, File outputFile);
}
