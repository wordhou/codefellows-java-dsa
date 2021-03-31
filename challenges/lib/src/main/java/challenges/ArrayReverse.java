package challenges;

import java.util.Arrays;

public class ArrayReverse {
  public static void main(String[] args) {
    int[] testArray = {1, 2, 3, 4, 5, 6, 7};
    int[] reversedArray = reverseArray(testArray);
    System.out.println(Arrays.toString(testArray));
    System.out.println(Arrays.toString(reversedArray));

    reverseArrayInPlace(testArray);
    System.out.println(Arrays.toString(testArray));
  }

  // Just use Arrays.toString()
  public static void printArray(int[] input) {
    System.out.print("[");
    for (int i = 0; i < input.length - 1; i++) {
      System.out.print(input[i] + ", ");
    }
    System.out.print(input[input.length - 1]);
    System.out.print("]");
    System.out.println();
  }

    // Makes a reversed copy of array
  public static int[] reverseArray(int[] input) {
    int[] reversed = new int[input.length];

    // Iterate over all array elements;
    for (int i = 0; i < input.length ; i++) {
      // Assign element in original array to reversed position in target array
      reversed[input.length - i - 1] = input[i];
    }

    return reversed;
  }

    // Reverses the array in place
  public static int[] reverseArrayInPlace(int[] array) {
    int temp;

    for (int i = 0; i < array.length / 2 ; i++) {
      // Swaps the elements at index i and length - 1 - i
      temp = array[i];
      array[i] = array[array.length - 1 - i];
      array[array.length - 1 - i] = temp;
    }

    return array;
  }

  public static void test () {
    int x = -5;
    // int division returns an int and rounds towards zero.
    System.out.println(x/2); 
  }
}
