package challenges.hashTable;

import java.util.Arrays;
import java.util.List;

public class HashTableSeparateChainingTest extends MapTest<String, Integer, HashTableSeparateChaining<String, Integer>> {

    @Override
    protected HashTableSeparateChaining<String, Integer> createInstance() {
        return new HashTableSeparateChaining<>();
    }

    @Override
    protected List<String> getDistinctKeys() {
        return Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "hello", "world", "test", "keys"
        );
    }

    @Override
    protected List<Integer> getDistinctValues() {
        return Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30
        );
    }
}