package challenges.utilities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static challenges.utilities.HashTableJoins.*;
import static org.junit.Assert.*;

public class HashTableJoinsTest {
    static Integer[][] keys1 = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
            {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5}
    };

    static Integer[][] values1 = {
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {3, 5, 2, 7, 6, 4, 1, 9, 8, 10},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {10, 10, 10, 10, 10},
            {4, 6, 8, 10, 12}
    };

    static Integer[][] keys2 = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 2, 3, 4, 5},
            {},
            {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
            {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
            {1, 3, 5}
    };

    static Integer[][] values2 = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {5, 5, 5, 5, 5},
            {},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {7, 4, 3},
    };

    static Integer[][] expectedKeys = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 2, 3, 4, 5},
            {},
            {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
            {2, 4},
            {1, 3, 5}
    };

    static Pair[][] expectedValues = {
            {
                    new Pair(10, 1),
                    new Pair(9, 2),
                    new Pair(8, 3),
                    new Pair(7, 4),
                    new Pair(6, 5),
                    new Pair(5, 6),
                    new Pair(4, 7),
                    new Pair(3, 8),
                    new Pair(2, 9),
                    new Pair(1, 10),
            },
            {
                    new Pair(3, 5),
                    new Pair(5, 5),
                    new Pair(2, 5),
                    new Pair(7, 5),
                    new Pair(6, 5),
            },

            {
            },

            {
                    new Pair(1, 10),
                    new Pair(2, 9),
                    new Pair(3, 8),
                    new Pair(4, 7),
                    new Pair(5, 6),
                    new Pair(6, 5),
                    new Pair(7, 4),
                    new Pair(8, 3),
                    new Pair(9, 2),
                    new Pair(10, 1),
            },

            {
                    new Pair(10, 1),
                    new Pair(10, 2),
            },

            {
                    new Pair(4, 7),
                    new Pair(8, 4),
                    new Pair(12, 3),
            },
    };

    List<Map<Integer, Integer>> maps1;
    List<Map<Integer, Integer>> maps2;
    List<Map<Integer, Pair<Integer, Integer>>> expected;

    @Before
    public void init() {
        maps1 = new ArrayList<>();
        maps2 = new ArrayList<>();
        expected = new ArrayList<>();
        for (int i = 0; i < keys1.length; i++) {
            Map<Integer, Integer> mapa = new HashMap<>();
            Map<Integer, Integer> mapb = new HashMap<>();
            Map<Integer, Pair<Integer, Integer>> mapc = new HashMap<>();
            for (int j = 0; j < keys1[i].length; j++) {
                mapa.put(keys1[i][j], values1[i][j]);
            }
            for (int k = 0; k < keys2[i].length; k++) {
                mapb.put(keys2[i][k], values2[i][k]);
            }
            for (int l = 0; l < expectedKeys[i].length; l++) {
                mapc.put(expectedKeys[i][l], expectedValues[i][l]);
            }
            maps1.add(mapa);
            maps2.add(mapb);
            expected.add(mapc);
        }
    }

    @Test
    public void leftJoin() {
        for (int i = 0; i < maps1.size(); i++) {
            assertEquals(expected.get(i), HashTableJoins.leftJoin(maps1.get(i), maps2.get(i)));
        }
    }
}