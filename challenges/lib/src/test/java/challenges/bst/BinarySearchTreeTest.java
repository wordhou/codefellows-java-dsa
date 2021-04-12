package challenges.bst;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void add() {
        BinarySearchTree<Integer> bst;

        bst = new BinarySearchTree<>();
        Arrays.asList(1,2,3).forEach(bst::add);
        assertEquals(Arrays.asList(1,2,3), bst.inOrderDepthFirstEnumeration());

        bst = new BinarySearchTree<>();
        Arrays.asList(4,2,3,7,1,9).forEach(bst::add);
        assertEquals(Arrays.asList(1,2,3,4,7,9), bst.inOrderDepthFirstEnumeration());

        bst = new BinarySearchTree<>();
        Arrays.asList(7,5,4,3,2,1).forEach(bst::add);
        assertEquals(Arrays.asList(1,2,3,4,5,7), bst.inOrderDepthFirstEnumeration());
    }

    @Test
    public void contains() {
        BinarySearchTree<Integer> bst;
        bst = new BinarySearchTree<>();
        assertFalse(bst.contains(1));
        Arrays.asList(1,2,3,2,8,7,9,10).forEach(bst::add);
        Arrays.asList(1,2,3,7,8,9,10).forEach(i -> assertTrue(bst.contains(i)));
        Arrays.asList(4,5,6).forEach(i -> assertFalse(bst.contains(i)));
    }
}