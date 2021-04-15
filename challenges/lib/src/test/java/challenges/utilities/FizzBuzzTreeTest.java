package challenges.utilities;

import challenges.tree.BTNode;
import challenges.tree.BinaryTree;
import challenges.tree.RTNode;
import challenges.tree.RoseTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FizzBuzzTreeTest {
    BinaryTree<Integer> binary;
    RoseTree<Integer> rose;

    @Before
    public void setUp() throws Exception {
        binary = new BinaryTree<>(new BTNode<>(1, new BTNode<>(3), new BTNode<>(2)));
        binary.root.left.left = new BTNode<>(15, new BTNode<>(12), new BTNode<>(10));
        binary.root.left.right = new BTNode<>(4);
        binary.root.right.left = new BTNode<>(7, new BTNode<>(8), new BTNode<>(30));
        binary.root.right.left.right = new BTNode<>(13, new BTNode<>(14, new BTNode<>(19)));
        binary.root.right.right = new BTNode<>(18);

        rose = new RoseTree<>();
        rose.root = new RTNode<Integer>(1);
        rose.root.children.append(new RTNode<Integer>(2));
        rose.root.children.get(0).children.append(new RTNode<Integer>(5));
        rose.root.children.get(0).children.append(new RTNode<Integer>(6));
        rose.root.children.append(new RTNode<Integer>(3));
        rose.root.children.get(1).children.append(new RTNode<Integer>(7));
        rose.root.children.get(1).children.get(0).children.append(new RTNode<Integer>(11));
        rose.root.children.append(new RTNode<Integer>(4));
        rose.root.children.get(1).children.append(new RTNode<Integer>(8));
        rose.root.children.get(1).children.append(new RTNode<Integer>(9));
        rose.root.children.get(1).children.append(new RTNode<Integer>(15));
    }

    @Test
    public void fizzBuzz() {
        LinkedList<String> expectedBinary = new LinkedList<>(Arrays.asList("1","Fizz","2","FizzBuzz","4","7","Fizz","Fizz","Buzz","8","13","14","19"));
        assertEquals(expectedBinary, FizzBuzzTree.fizzBuzz(binary).breadthFirstEnumeration());

        LinkedList<String> expectedRose = new LinkedList<>(Arrays.asList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","FizzBuzz","11"));
        assertEquals(expectedRose, FizzBuzzTree.fizzBuzz(rose).breadthFirstEnumeration());
    }

    @Test
    public void testFizzBuzz() {
    }
}