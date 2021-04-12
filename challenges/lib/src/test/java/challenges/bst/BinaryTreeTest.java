package challenges.bst;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    BinaryTree<Integer> empty;
    BinaryTree<Integer> small;
    BinaryTree<Integer> medium;
    BinaryTree<Character> large;

    @Before
    public void makeLists() {
        empty = new BinaryTree<>(null);
        small = new BinaryTree<>(new BTNode<>(1, new BTNode<>(2, new BTNode<>(3)), new BTNode<>(4)));

        medium = new BinaryTree<>(new BTNode<>(1));
        medium.root.left = new BTNode<>(2, new BTNode<>(4), new BTNode<>(5));
        medium.root.right = new BTNode<>(3, new BTNode<>(6), new BTNode<>(7));

        large = new BinaryTree<>(new BTNode<>('h', new BTNode<>('c'), new BTNode<>('n')));
        large.root.left.left = new BTNode<>('g', new BTNode<>('z'), new BTNode<>('a'));
        large.root.left.right = new BTNode<>('z');
        large.root.right.left = new BTNode<>('x', new BTNode<>('p'), new BTNode<>('a'));
        large.root.right.left.right = new BTNode<>('a', new BTNode<>('a', new BTNode<>('a')));
        large.root.right.right = new BTNode<>('b');
    }

    @Test
    public void breadthFirstEnumeration() {
        assertTrue("enumeration on empty tree to return empty list",
                empty.breadthFirstEnumeration().isEmpty());
        assertEquals(Arrays.asList(1, 2, 4, 3), small.breadthFirstEnumeration());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), medium.breadthFirstEnumeration());
        String result = "";
        List<Character> chars = large.breadthFirstEnumeration();
        for (Character c : chars) { result += c; }
        assertEquals("hcngzxbzapaaa", result);
    }

    @Test
    public void preOrderDepthFirstEnumeration() {
        assertTrue("enumeration on empty tree to return empty list",
                empty.preOrderDepthFirstEnumeration().isEmpty());
        assertEquals(Arrays.asList(1, 2, 3, 4), small.preOrderDepthFirstEnumeration());
        assertEquals(Arrays.asList(1, 2, 4, 5, 3, 6, 7), medium.preOrderDepthFirstEnumeration());
        String result = "";
        List<Character> chars = large.preOrderDepthFirstEnumeration();
        for (Character c : chars) { result += c; }
        assertEquals("hcgzaznxpaaab", result);
    }

    @Test
    public void inOrderDepthFirstEnumeration() {
        assertTrue("enumeration on empty tree to return empty list",
                empty.inOrderDepthFirstEnumeration().isEmpty());
        assertEquals(Arrays.asList(3, 2, 1, 4), small.inOrderDepthFirstEnumeration());
        assertEquals(Arrays.asList(4, 2, 5, 1, 6, 3, 7), medium.inOrderDepthFirstEnumeration());
        String result = "";
        List<Character> chars = large.inOrderDepthFirstEnumeration();
        for (Character c : chars) { result += c; }
        assertEquals("zgaczhpxaaanb", result);
    }

    @Test
    public void postOrderDepthFirstEnumeration() {
        assertTrue("enumeration on empty tree to return empty list",
                empty.postOrderDepthFirstEnumeration().isEmpty());
        assertEquals(small.postOrderDepthFirstEnumeration(), Arrays.asList(3, 2, 4, 1));
        assertEquals(medium.postOrderDepthFirstEnumeration(), Arrays.asList(4, 5, 2, 6, 7, 3, 1));
        String result = "";
        List<Character> chars = large.postOrderDepthFirstEnumeration();
        for (Character c : chars) { result += c; }
        assertEquals("zagzcpaaaxbnh", result);
    }

    @Test
    public void getMaximumBy() {
        assertThrows("binop reduce of empty tree throws",
                NoSuchElementException.class, () -> empty.findMaximumBy(Comparator.naturalOrder()));
        assertEquals("finds maximum of tree", 4, (int) small.findMaximumBy(Comparator.naturalOrder()));
        assertEquals("finds maximum of tree", 7, (int) medium.findMaximumBy(Comparator.naturalOrder()));
        assertEquals("finds maximum of tree", 'z', (char) large.findMaximumBy(Comparator.naturalOrder()));
    }
}