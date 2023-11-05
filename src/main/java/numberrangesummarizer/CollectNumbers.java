package numberrangesummarizer;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class CollectNumbers implements NumberRangeSummarizer {
    // The regex pattern we allow for our comma seperated input
    private static final Pattern COMMA_SEPARATED_PATTERN = Pattern.compile("^-?\\d+(,-?\\d+)*$");

    /* Assumptions for collect:
    /  - We want an empty array list returned if an empty string or a null string is used as input
    /  - If any unnecessary whitespace is found we remove it
    /  - We want a comma seperated list of integers as input, if the input does not match that pattern (after we removed the whitespace) we want to throw an IllegalArgumentException
    /  - collect does not have to sort the input (if we wanted to we could simply add a .sorted() to the stream)
    */
    @Override
    public Collection<Integer> collect(String input) {
        // If the input is null/empty return empty list
        if (input == null || input.isEmpty()) {
            return new ArrayList<Integer>();
        }

        // if there was any whitespace in the string, remove it
        input = input.replaceAll("\\s", "");

        // If the input does not match our allowed pattern, throw an error
        if (!COMMA_SEPARATED_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("The input doesn't match the expected pattern.");
        }

        // uses a stream to collect the numbers from the string
        List<Integer> collectedList = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .distinct() // remove any duplicates
                .collect(Collectors.toList());

        return collectedList;
    }

    /* Assumptions for summarizeCollection:
    /  - If we receive an empty collection we want to return an empty String
    */
    @Override
    public String summarizeCollection(Collection<Integer> input) {

        if (input.isEmpty()) {
            return "";
        }
        ArrayList<Integer> inputArrList = new ArrayList<>(input);

        // Sort the input for easier processing
        Collections.sort(inputArrList);

        String ranges = "";

        // start of the range
        int start = inputArrList.get(0);

        // Initialize the end of the current range with the start
        int end = start;

        // Loop through the elements in the ArrayList, starting from the second element
        for (int i = 1; i < inputArrList.size(); i++) {
            int current = inputArrList.get(i);

            // Check if the current element is one greater than the previous element
            if (end + 1 == current) {
                end = current;
                continue;
            } else if (start == end) {
                // If start and end are the same, it's a single number
                ranges += start + ", ";
            } else {
                // If start and end are different, it's a range
                ranges += start + "-" + end + ", ";
            }

            // Set both start and end to the current element for the next range/entry
            start = current;
            end = current;
        }

        // After the loop, handle the last range or single number
        if (start == end) {
            ranges += start;
        } else {
            ranges += start + "-" + end;
        }

        return ranges;
    }
}