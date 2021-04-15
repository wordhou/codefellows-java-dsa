package challenges.utilities;

import challenges.tree.BinaryTree;
import challenges.tree.RoseTree;

public class FizzBuzzTree {
    public static BinaryTree<String> fizzBuzz(BinaryTree<Integer> tree) {
        return tree.map(n -> n % 15 == 0 ? "FizzBuzz" : n % 5 == 0 ? "Buzz" : n % 3 == 0 ? "Fizz" : "" + n);
    }

    public static RoseTree<String> fizzBuzz(RoseTree<Integer> tree) {
        return tree.map(n -> n % 15 == 0 ? "FizzBuzz" : n % 5 == 0 ? "Buzz" : n % 3 == 0 ? "Fizz" : "" + n);
    }
}
