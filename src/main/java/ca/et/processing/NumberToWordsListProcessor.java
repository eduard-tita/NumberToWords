package ca.et.processing;

import java.util.List;

/**
 * It transforms to English equivalents all the numbers provided.
 * The results are aggregated in a list.
 *
 * @author Eduard Tita
 */
public interface NumberToWordsListProcessor {

    /**
     * It transforms to English equivalents all the numbers provided.
     * @param numbers list of numbers to be transformed
     * @return transformation results; each list element corresponds to one element the input
     */
    List<String> transform(List<String> numbers);

}
