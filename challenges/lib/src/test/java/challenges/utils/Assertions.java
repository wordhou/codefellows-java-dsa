package challenges.utils;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class Assertions {

    public static void assertArraysHaveSameElements(int[] expected, int[] actual) {
        int[] expectedSorted = expected.clone();
        int[] actualSorted = expected.clone();
        Arrays.sort(expectedSorted);
        Arrays.sort(actualSorted);
        assertArrayEquals(expectedSorted, actualSorted);
    }

    public static void assertArraysHaveSameElements(String message, int[] expected, int[] actual) {
        int[] expectedSorted = expected.clone();
        int[] actualSorted = expected.clone();
        Arrays.sort(expectedSorted);
        Arrays.sort(actualSorted);
        assertArrayEquals(message, expectedSorted, actualSorted);
    }

    public static <T> void assertCollectionsHaveSameElements(Collection<T> expected, Collection<T> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }
}
