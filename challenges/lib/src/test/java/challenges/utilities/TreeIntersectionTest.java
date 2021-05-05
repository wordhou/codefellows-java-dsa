package challenges.utilities;

import challenges.tree.BinarySearchTree;
import challenges.tree.BinaryTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TreeIntersectionTest {

    @Test
    public void treeIntersection() {
        Integer[][] arrays1 = new Integer[][] {
                {1,2,3,4,5},
                {4,3,2,4,5,6},
                {8,5,6,1,3,2,4,5,7,8,9,10},
                {8,5,6,1,3,2,4,5,7,8,9,10},
                {8,5,6,1,3,2,4,5,7,8,9,10},
                {8,5,6,1,3,2,4,5,7,8,9,10},
                {8,5,6,1,3,2,4,5,7,8,9,10},
                {8,5,6,1,3,2,4,5,7,8,9,10},
        };

        Integer[][] arrays2 = new Integer[][] {
                {4,5},
                {4,5,6},
                {22},
                {22,1,3},
                {6,6,6,6},
                {7,7,7,14,19,12,2,3},
                {3,10,11,14,2,3,1,4},
                {8,9,11,14,12,5}
        };

        Integer[][] expected = new Integer[][] {
                {4,5},
                {4,5,6},
                {},
                {1,3},
                {6},
                {7,2,3},
                {3,10,2,1,4},
                {8,9,5}
        };

        for (int i = 0; i < arrays1.length; i++) {
            BinaryTree<Integer> tree1 = new BinarySearchTree<>(Arrays.asList(arrays1[i]));
            BinaryTree<Integer> tree2 = new BinarySearchTree<>(Arrays.asList(arrays2[i]));
            Set<Integer> intersection = new HashSet<>(Arrays.asList(expected[i]));
            assertEquals(intersection, TreeIntersection.treeIntersection(tree1, tree2));
        }
    }
}