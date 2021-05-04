package challenges.utilities;

import challenges.tree.BinarySearchTree;
import challenges.tree.BinaryTree;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TreeIntersection {
    static public <T> Set<T> treeIntersection(BinaryTree<T> first, BinaryTree<T> second) {
        Set<T> firstSet = new HashSet<>(first.breadthFirstEnumeration());
        return StreamSupport.stream(second.spliterator(), false)
                .filter(firstSet::contains).collect(Collectors.toSet());
    }
}