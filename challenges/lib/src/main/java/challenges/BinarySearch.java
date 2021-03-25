package challenges;

public class BinarySearch {
    public static int binarySearch(int[] array, int num) {
        // Reject cases that we know are not going to work.
        if (array.length == 0
                || num < array[0]
                || num > array[array.length - 1]) return -1;

        // Check the first and last element of the array
        if (num == array[0]) return 0;
        if (num == array[array.length-1]) return array.length - 1;

        // Setup the low, middle, and high variables for the loop
        int low = 0;
        int high = array.length - 1;
        int middle;
        while (high > low + 1) {
            middle = (low + high) / 2;
            if (array[middle] == num) return middle;
            if (array[middle] < num) low = middle;
            if (array[middle] > num) high = middle;
        }

        // Check the last two indices for our element before return -1
        if (num == array[low]) return low;
        if (num == array[high]) return high;
        return -1;
    }
}