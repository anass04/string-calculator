package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static string.StringUtils.escapeSpecialCharacters;

public class CustomDelimiterWithHooksParser {

    private static final String DELIMITER_WITH_HOOKS_REGEXP = "\\[(.*?|\n)]";
    private static final int DELIMITER_GROUP_INDEX = 1;
    private static final int NUMBER_GROUP_INDEX = 3;

    /**
     * Allow to separate the delimiters part and the expression part for custom delimiter with hooks
     *
     * @param customDelimiterMatcher the matcher with EXPRESSION_WITH_CUSTOM_DELIMITER_AND_HOOKS_REGEXP regex value
     * @return a map with single entry, the key is the expression to sum and the value is the delimiters list
     */
    public static Section getSectionsWithHooksInDelimiter(Matcher customDelimiterMatcher) {
        List<String> delimiters = new ArrayList<>();
        String delimiter = customDelimiterMatcher.group(DELIMITER_GROUP_INDEX);
        String numbersToAdd = customDelimiterMatcher.group(NUMBER_GROUP_INDEX);
        Matcher second = Pattern.compile(DELIMITER_WITH_HOOKS_REGEXP).matcher(delimiter);
        while (second.find()) {
            String delimiterPart2 = second.group();
            delimiters.add(escapeSpecialCharacters(getDelimiterWithoutHooks(delimiterPart2)));
        }
        return new Section(numbersToAdd, delimiters);
    }

    private static String getDelimiterWithoutHooks(String delimiter) {
        return delimiter.substring(1, delimiter.length() - 1);
    }
}
