package challenges.sorting;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    /**
     * Sorts an list of comparable items in place.
     *
     * The algorithm maintains the property that after the `i`th step, the first `i` elements are sorted.
     * Each step, the `i+1`th element is inserted into into the first `i` elements
     * @param arr The list to sort.
     * */
    static <T extends Comparable<T>> void insertionSort(List<T> arr) {
        for (int i = 1; i < arr.size(); i++) {
            int j = i - 1;
            T temp = arr.get(i);

            while (j >= 0 && temp.compareTo(arr.get(j)) < 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }

            arr.set(j + 1, temp);
        }
    }

    /**
     * Sorts an list of ints in place with insertion sort.
     * @param arr The array to sort
     * */
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i];

            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = temp;
        }
    }
}
