package numberrangesummarizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

public class CollectNumbersTest {

    private CollectNumbers collector;

    @BeforeEach
    void setUp() {
        collector = new CollectNumbers();
    }

    @Test
    @DisplayName("collect should return the correct output for valid input")
    public void collectValidInput() {
        String input = "-10,0,1,2,4,5,8";
        Collection<Integer> result = collector.collect(input);
        List<Integer> expected = new ArrayList<>(List.of(-10, 0, 1, 2, 4, 5, 8));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("collect should return the correct output for empty input")
    public void collectEmptyInput() {
        String input = "";
        Collection<Integer> result = collector.collect(input);
        Collection<Integer> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("collect should return the correct output for null input")
    public void collectNullInput() {
        String input = null;
        Collection<Integer> result = collector.collect(input);
        Collection<Integer> expected = new ArrayList<>();
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("collect should return the correct output for input with whitespace")
    public void collectInputWithWhitespace() {
        String input = "  -10, 0,1, \n2,\t 4, 5, 8";
        Collection<Integer> result = collector.collect(input);
        List<Integer> expected = new ArrayList<>(List.of(-10, 0, 1, 2, 4, 5, 8));
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("collect should throw an IllegalArgumentException exception if invalid input is provided")
    public void collectInvalidInput() {
        CollectNumbers collector = new CollectNumbers();

        String invalidInput1 = "someInvalidInputHere123";
        try {
            collector.collect(invalidInput1);
            // If we reach here the exception was not thrown and the test should fail
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("The input doesn't match the expected pattern.", e.getMessage());
        }

        String invalidInput2 = ",123,";
        try {
            collector.collect(invalidInput2);
            // If we reach here the exception was not thrown and the test should fail
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("The input doesn't match the expected pattern.", e.getMessage());
        }
    }

    @Test
    @DisplayName("collect should return the correct output for input with duplicates")
    public void collectInputWithDuplicates() {
        String input = "1,2,2,3,3,4,4";
        Collection<Integer> result = collector.collect(input);
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("summarizeCollection should return an empty string for empty input")
    public void summarizeCollectionEmptyInput() {
        Collection<Integer> input = new ArrayList<>();
        String result = collector.summarizeCollection(input);
        assertEquals("", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for valid input")
    public void summarizeCollectionValidInput() {
        Collection<Integer> input = new ArrayList<>(List.of(1, 2, 3, 5, 6, 7, 9));
        String result = collector.summarizeCollection(input);
        assertEquals("1-3, 5-7, 9", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for input without consecutive numbers")
    public void summarizeCollectionNonConsecutiveNumbers() {
        Collection<Integer> input = new ArrayList<>(List.of(1, 3, 5, 7));
        String result = collector.summarizeCollection(input);
        assertEquals("1, 3, 5, 7", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for input with bot consecutive and nonconsecutive numbers")
    public void summarizeCollectionConsecutiveAndNonConsecutiveNumbers() {
        Collection<Integer> input = new ArrayList<>(List.of(1, 2, 4, 5, 7, 9));
        String result = collector.summarizeCollection(input);
        assertEquals("1-2, 4-5, 7, 9", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for input with negative numbers")
    public void summarizeCollectionNegativeNumbers() {
        Collection<Integer> input = new ArrayList<>(List.of(-8, -6, -3, -2));
        String result = collector.summarizeCollection(input);
        assertEquals("-8, -6, -3--2", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for input with negative and positive numbers")
    public void summarizeCollectionPositiveAndNegativeNumbers() {
        Collection<Integer> input = new ArrayList<>(List.of(-3, -2, 0, 4, 5, 7, 9));
        String result = collector.summarizeCollection(input);
        assertEquals("-3--2, 0, 4-5, 7, 9", result);
    }

    @Test
    @DisplayName("summarizeCollection should return the correct output for unsorted input")
    public void summarizeCollectionUnsorted() {
        Collection<Integer> input = new ArrayList<>(List.of(9, -3, -2, 6 , 5, 12));
        String result = collector.summarizeCollection(input);
        assertEquals("-3--2, 5-6, 9, 12", result);
    }

}
