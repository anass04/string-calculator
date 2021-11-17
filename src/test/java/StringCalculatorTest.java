import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class allow to test StringCalculator add method
 */
public class StringCalculatorTest {

    @Test
    void add_empty_string_should_return_0() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void add_only_one_number_should_return_itself() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    void add_two_numbers_with_delimiter_should_return_correct_value() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    void add_with_null_value_should_throw_illegal_argument_exception() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(null));
    }

    @Test
    void add_unknown_amount_of_numbers_should_return_correct_value() {
        assertEquals(10, StringCalculator.add("1,2,3,4"));
    }

    @Test
    void add_numbers_with_new_line_delimiter_should_return_correct_value() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    void add_malformed_input_should_throw_illegal_argument_exception() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("1,\n"));
    }

    @Test
    void add_with_one_custom_delimiter_should_return_correct_value() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    void add_custom_delimiter_with_wrong_syntax_should_throw_illegal_argument_exception() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(";\n1;2"));
    }

    @Test
    void add_negative_number_should_throw_an_exception_negatives_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("//;\n1;-2"));
    }

    @Test
    void add_should_ignore_number_bigger_than_1000() {
        assertEquals(2, StringCalculator.add("//;\n2;1001"));
    }

    @Test
    void add_with_any_length_of_delimiters_should_return_correct_value() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    void add_with_multiple_delimiters_should_return_correct_value() {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    void add_with_multiple_delimiters_with_length_longer_than_one_should_return_correct_value() {
        assertEquals(6, StringCalculator.add("//[abcd][EFG]\n1abcd2EFG3"));
    }

    @Test
    void add_with_special_characters_delimiters_should_return_correct_value() {
        assertEquals(6, StringCalculator.add("//[??][..]\n1??2..3"));
    }

}
