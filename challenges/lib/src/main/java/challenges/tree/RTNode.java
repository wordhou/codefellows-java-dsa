package challenges.tree;

import challenges.linkedList.LinkedList;

class RTNode<T> {
    public T value;
    public LinkedList<RTNode<T>> children;

    public RTNode(){
        children = new LinkedList<>();
    }
}
