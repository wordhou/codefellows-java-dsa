package challenges.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {

    private <T extends Comparable<T>> boolean isSorted(List<T> arr) {
        for (int i = 1; i < arr.size(); i++)
            if (arr.get(i - 1).compareTo(arr.get(i)) > 0) return false;
        return true;
    }

    @Test
    public void isSortedWorks() {
        assertTrue(isSorted(Arrays.asList(1)));
        assertTrue(isSorted(Arrays.asList(1, 1, 1)));
        assertTrue(isSorted(Arrays.asList(1, 2, 3)));
        assertFalse(isSorted(Arrays.asList(1, 2, 1)));
        assertFalse(isSorted(Arrays.asList(3, 2)));
        assertFalse(isSorted(Arrays.asList(1, 2, 3, 0)));
        assertFalse(isSorted(Arrays.asList(1, 3, 2, 4)));
    }

    @Test
    public void insertionSort() {
        List<List<Integer>> lists1 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 2, 1),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 3, 1),
                Arrays.asList(2, 1, 3)
        );

        List<Integer> expected1 = Arrays.asList(1, 2, 3);

        List<List<Integer>> lists2 = Arrays.asList(
                Arrays.asList(5, 4, 3, 2, 1),
                Arrays.asList(5, 1, 2, 3, 4),
                Arrays.asList(4, 3, 2, 1, 5),
                Arrays.asList(1, 3, 2, 5, 4),
                Arrays.asList(2, 5, 4, 3, 1)
        );

        List<Integer> expected2 = Arrays.asList(1, 2, 3, 4, 5);

        lists1.forEach(l -> {
            InsertionSort.insertionSort(l);
            assertEquals(expected1, l);
        });

        lists2.forEach(l -> {
            InsertionSort.insertionSort(l);
            assertEquals(expected2, l);
        });
    }
}