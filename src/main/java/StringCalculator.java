import string.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static parser.StringCalculatorParser.getSection;
import static string.StringUtils.countMatching;
import static string.StringUtils.isNumeric;

/**
 * This class allows to add numbers separated by delimiters
 */
public class StringCalculator {

    private static final Integer MAX_NUMBER_TO_ADD = 1000;

    /**
     * Allow to add numbers inside string
     *
     * @param numbers the string to process
     * @return the addition result
     */
    public static int add(String numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }
        if (numbers.isEmpty()) {
            return 0;
        }
        if (isNumeric(numbers)) {
            var number = Integer.parseInt(numbers);
            if (isGreaterThanMaxNumber(number)) {
                return 0;
            } else {
                return number;
            }
        }

        var section = getSection(numbers);
        var delimitersSplitRegex = String.join("|", section.getDelimiters());
        var numbersToAdd = section.getNumbers();
        var splitTerms = numbersToAdd.split(delimitersSplitRegex);

        if (!isDelimiterNumberValid(numbersToAdd, delimitersSplitRegex, splitTerms.length - 1)) {
            throw new IllegalArgumentException("The chain is not correct");
        }

        var negativeValues = getNegativeValues(splitTerms);
        if (!negativeValues.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed :" + String.join(",", negativeValues));
        }

        try {
            return sumTerms(splitTerms);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The chain is not correct");
        }
    }

    /**
     * Return the terms sum, for numbers not greater than MAX_NUMBER_TO_ADD
     *
     * @param terms the terms to sum
     * @return the sum result
     */
    private static Integer sumTerms(String[] terms) {
        return Arrays.stream(terms).map(n -> {
            int value = Integer.parseInt(n);
            if (isGreaterThanMaxNumber(value)) {
                return 0;
            }
            return value;
        }).reduce(0, Integer::sum);
    }

    private static boolean isDelimiterNumberValid(String numbersToAdd, String delimitersSplitRegex, int splitTermNumber) {
        return countMatching(numbersToAdd, delimitersSplitRegex) == splitTermNumber;
    }

    private static boolean isGreaterThanMaxNumber(int value) {
        return value > MAX_NUMBER_TO_ADD;
    }

    private static List<String> getNegativeValues(String[] terms) {
        return Arrays.stream(terms).filter(StringUtils::isNegativeNumber).collect(Collectors.toList());
    }

}
