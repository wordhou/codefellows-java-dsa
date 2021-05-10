package challenges.stacksQueues;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntDynamicArray implements IntStack {
    private static final int INIT_CAPACITY = 2;
    private int numElements = 0;
    private int[] array = new int[INIT_CAPACITY];

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Nullable
    @Override
    public int peek() {
        if (numElements == 0) throw new NoSuchElementException();
        return array[numElements - 1];
    }

    @Override
    public void push(int item) {
        if (numElements == array.length)
            array = Arrays.copyOfRange(array, 0, array.length * 2);
        array[numElements++] = item;
    }

    @Override
    public int pop() throws NoSuchElementException {
        if (numElements == 0) throw new NoSuchElementException();
        return array[--numElements];
    }
}
