package challenges;

import challenges.utilities.BinarySearch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {
    @Test
    public void testBinarySearchElementInArray() {
        int[][] testInputs = {
            {1},
            {1, 3},
            {1, 3, 5},
            {1, 3, 5, 7, 9, 11, 13, 15},
        };

        for (int[] input : testInputs) {
            for (int i = 0; i < input.length - 1; i++) {
                assertEquals("returns the index of the element when it's in the array",
                        i, BinarySearch.binarySearch(input, input[i]));
            }
        }
    }

    @Test
    public void testBinarySearchElementNotInArray() {
        int[] input1 = {1, 3, 5, 7, 9, 11, 13, 15};
        int expected = -1;

        int[] notInArray = {2, 4, 15, -5, 25};

        for (int num : notInArray) {
            assertEquals("returns -1 when element is not in array",
                    BinarySearch.binarySearch(input1, num), expected);
        }
    }
}
