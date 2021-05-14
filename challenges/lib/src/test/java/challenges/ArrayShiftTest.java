package challenges;

import challenges.utilities.ArrayShift;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayShiftTest {
    @Test public void testInsertShiftArray() {
        int[] original1 = {2, 4, 6, 8};
        int[] expected1 = {2, 4, 5, 6, 8};
        assertArrayEquals("Should insert into the middle of an even number of elements",
                ArrayShift.insertShiftArray(original1, 5), expected1);

        int[] original2 = {4, 8, 15, 23, 42};
        int[] expected2 = {4, 8, 15, 16, 23, 42};
        assertArrayEquals("Should insert after the middle element in an odd number of elements",
                ArrayShift.insertShiftArray(original2, 16), expected2);

        int[] original3 = {};
        int[] expected3 = {1};
        assertArrayEquals("Should work on an empty array",
                ArrayShift.insertShiftArray(original3, 1), expected3);
    }
}