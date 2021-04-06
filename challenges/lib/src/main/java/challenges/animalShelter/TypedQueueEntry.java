package challenges.animalShelter;

public class TypedQueueEntry<T> {
    T item;
    long entered;

    public TypedQueueEntry(T item, long entered) {
        this.item = item;
        this.entered = entered;
    }
}
