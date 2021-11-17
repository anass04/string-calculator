package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.CustomDelimiterWithHooksParser.getSectionsWithHooksInDelimiter;
import static parser.CustomDelimiterWithoutHooksParser.getSectionsWithoutHooksInDelimiter;

public class StringCalculatorParser {

    private static final String EXPRESSION_WITH_CUSTOM_DELIMITER_AND_HOOKS_REGEXP = "//(\\[(.*\\s*)?])*\n(.*)";

    /**
     * Allow to separate the delimiter and the expression part
     *
     * @param numbers the string containing optional delimiter and terms to sum
     * @return a map with single entry, the key is the expression to sum and the value is the delimiters list
     */
    public static Section getSection(String numbers) {
        Matcher matcher = Pattern.compile(EXPRESSION_WITH_CUSTOM_DELIMITER_AND_HOOKS_REGEXP).matcher(numbers);
        if (matcher.matches()) {
            return getSectionsWithHooksInDelimiter(matcher);
        } else {
            return getSectionsWithoutHooksInDelimiter(numbers);
        }
    }

}
