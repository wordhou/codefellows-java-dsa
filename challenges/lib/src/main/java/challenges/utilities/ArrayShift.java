package challenges.utilities;

public class ArrayShift {
    public static int[] insertShiftArray(int[] input, int element) {
        // Initialize a new array for the result
        int[] output = new int[input.length + 1];

        // Finds the index that we want to insert the new element at
        int middleIndex = (input.length + 1)/ 2;

        output[middleIndex] = element;

        for (int i = 0; i < input.length; i++) {
            if (i < middleIndex)
                output[i] = input[i];
            else
                output[i+1] = input[i];
        }

        return output;
    }
}
