package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final String NUMBER_REGEXP = "[0-9]+";
    private static final String NEGATIVE_NUMBER_REGEXP = "-[0-9]+";
    private static final Set<Character> SPECIAL_CHARACTERS = new HashSet<>(Arrays.asList('.', '*', '+', '?'));

    public static boolean isNumeric(String str) {
        return str.matches(NUMBER_REGEXP);
    }

    public static boolean isNegativeNumber(String str) {
        if (str == null) {
            return false;
        }
        return str.matches(NEGATIVE_NUMBER_REGEXP);
    }

    public static String escapeSpecialCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (SPECIAL_CHARACTERS.contains(ch)) {
                sb.append("\\");
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static long countMatching(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.results().count();
    }

}
