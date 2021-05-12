package challenges.stacksQueues;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A min-priority queue that accepts items labeled from 0 to n-1, with double weights that supports a O(log n) reduce-weight operation.
 * <p>
 * Takes O(n) space.
 */
public class IndexedDoublePriorityQueue {
    static final int INIT_CAPACITY = 10;
    int numElements = 0;
    int[] labels = new int[INIT_CAPACITY];
    double[] values = new double[INIT_CAPACITY];
    int[] heapIndex;

    public IndexedDoublePriorityQueue(int size) {
        heapIndex = new int[size];
        Arrays.fill(heapIndex, -1);
    }

    public void add(int label, double weight) {
        if (heapIndex[label] == -1) {
            if (numElements == labels.length) expandHeap();
            labels[numElements] = label;
            values[numElements] = weight;
            heapIndex[label] = numElements;
            numElements++;
            siftUp(numElements - 1);
        }
    }

    public boolean isEmpty() {
        return numElements == 0;
    }

    public int size() {
        return numElements;
    }

    public int findMinIndex() {
        if (numElements == 0) throw new NoSuchElementException();
        return labels[0];
    }

    public double findMinWeight() {
        if (numElements == 0) throw new NoSuchElementException();
        return values[0];
    }

    public int removeMin() {
        if (numElements == 0) throw new NoSuchElementException();
        int label = labels[0];
        heapIndex[label] = -1;
        swap(--numElements, 0);
        siftDown(0);
        return label;
    }

    public void update(int label, double weight) {
        int i = heapIndex[label];
        if (i < 0 || weight > values[i]) return;
        values[i] = weight;
        siftUp(i);
    }

    /**
     * Adds an item to the queue if it doesn't exist, and updates it if it does exist. If the new weight is greater than the old weight, does nothing.
     *
     * @param label
     * @param weight
     */
    public void put(int label, double weight) {
        if (heapIndex[label] == -1) {
            add(label, weight);
        } else {
            update(label, weight);
        }
    }

    private void expandHeap() {
        labels = Arrays.copyOfRange(labels, 0, labels.length * 2);
        values = Arrays.copyOfRange(values, 0, values.length * 2);
    }

    private void contractHeap() {
        labels = Arrays.copyOfRange(labels, 0, labels.length / 2);
        values = Arrays.copyOfRange(values, 0, values.length / 2);
    }

    private void siftUp(int i) {
        while (i > 0) {
            int j = (i - 1) / 2;
            if (values[i] < values[j]) {
                swap(i, j);
                i = j;
            } else break;
        }
    }

    private void siftDown(int i) {
        while (2 * i + 1 < numElements) {
            int j = 2 * i + 2 < numElements && values[2 * i + 2] < values[2 * i + 1]
                    ? 2 * i + 2
                    : 2 * i + 1;
            if (values[i] > values[j]) {
                swap(i, j);
                i = j;
            } else break;
        }
    }

    private void swap(int i, int j) {
        heapIndex[labels[i]] = j;
        heapIndex[labels[j]] = i;
        double tempValue = values[j];
        int tempLabel = labels[j];
        values[j] = values[i];
        values[i] = tempValue;
        labels[j] = labels[i];
        labels[i] = tempLabel;
    }
}
