package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static string.StringUtils.escapeSpecialCharacters;

public class CustomDelimiterWithoutHooksParser {

    private static final String DEFAULT_DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    private static final String SINGLE_DELIMITER_WITHOUT_HOOKS_REGEXP = "//(.*?|\n)\n(.*)";
    private static final int DELIMITER_GROUP_INDEX = 1;
    private static final int NUMBER_GROUP_INDEX = 2;

    /**
     * Allow to separate the delimiters part and the expression part when hooks are not present
     *
     * @param numbers the string containing optional delimiter and terms to sum
     * @return a map with single entry, the key is the expression to sum and the value is the delimiters list
     */
    public static Section getSectionsWithoutHooksInDelimiter(String numbers) {
        List<String> delimiters = new ArrayList<>();
        Matcher singleDelimiterMatcher = Pattern.compile(SINGLE_DELIMITER_WITHOUT_HOOKS_REGEXP).matcher(numbers);
        if (singleDelimiterMatcher.matches()) {
            String delimiterPart = singleDelimiterMatcher.group(DELIMITER_GROUP_INDEX);
            delimiters.add(escapeSpecialCharacters(delimiterPart));
            String numbersToAdd = singleDelimiterMatcher.group(NUMBER_GROUP_INDEX);
            return new Section(numbersToAdd, delimiters);
        } else {
            return getSectionsWithoutCustomDelimiter(numbers);
        }
    }

    private static Section getSectionsWithoutCustomDelimiter(String numbers) {
        return new Section(numbers, Arrays.asList(DEFAULT_DELIMITER, NEW_LINE));
    }
}
