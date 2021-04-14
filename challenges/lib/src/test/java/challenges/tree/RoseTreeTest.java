package challenges.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoseTreeTest {
    RoseTree<Integer> empty;
    RoseTree<Integer> small;
    RoseTree<Integer> medium;

    @Before
    public void setUp() throws Exception {
        empty = new RoseTree<>();

        small = new RoseTree<>();
        small.root = new RTNode<Integer>(1);
        small.root.children.append(new RTNode<Integer>(2));
        small.root.children.append(new RTNode<Integer>(3));
        small.root.children.append(new RTNode<Integer>(4));

        medium = new RoseTree<>();
        medium.root = new RTNode<Integer>(1);
        medium.root.children.append(new RTNode<Integer>(2));
        medium.root.children.get(0).children.append(new RTNode<Integer>(5));
        medium.root.children.get(0).children.append(new RTNode<Integer>(6));
        medium.root.children.append(new RTNode<Integer>(3));
        medium.root.children.get(1).children.append(new RTNode<Integer>(7));
        medium.root.children.get(1).children.get(0).children.append(new RTNode<Integer>(11));
        medium.root.children.append(new RTNode<Integer>(4));
        medium.root.children.get(1).children.append(new RTNode<Integer>(8));
        medium.root.children.get(1).children.append(new RTNode<Integer>(9));
        medium.root.children.get(1).children.append(new RTNode<Integer>(10));
    }

    @Test
    public void equals() {
        assertFalse("small does not equals null", small.equals(null));
        assertFalse("small does not equal empty", small.equals(empty));
        assertTrue("empty equals itself", empty.equals(empty));
        assertTrue("empty equals new empty", empty.equals(new RoseTree<Integer>()));
    }

    @Test
    public void preOrderDepthFirstTraversalForEach() {
    }

    @Test
    public void map() {
    }

    @Test
    public void testMap() {
    }

    @Test
    public void breadthFirstTraversal() {
    }

    @Test
    public void testBreadthFirstTraversal() {
    }

    @Test
    public void testBreadthFirstTraversal1() {
    }

    @Test
    public void testPreOrderDepthFirstTraversal() {
    }
}