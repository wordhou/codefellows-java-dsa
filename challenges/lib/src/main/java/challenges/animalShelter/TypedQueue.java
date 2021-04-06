package challenges.animalShelter;

import challenges.stacksQueues.LinkedListQueue;
import challenges.stacksQueues.Queue;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TypedQueue {
    long count = 0;
    Map<Class, LinkedListQueue<TypedQueueEntry>> queues = new HashMap<>();

    public TypedQueue (Class[] classes) {
        for (Class clazz : classes) {
            queues.put(clazz, new LinkedListQueue<TypedQueueEntry>());
        }
    }

    void enqueue(Object item) throws Exception {
        if (! queues.containsKey(item.getClass())) {
            throw new Exception("Class is not one of the classes of this TypedQueue");
        }
        queues.get(item.getClass()).enqueue(new TypedQueueEntry(item, count++));
    }

    public <T> T dequeue(Class<T> clazz) throws Exception {
        if (! queues.containsKey(clazz))
            throw new Exception("Class is not one of the classes of this TypedQueue");
        Queue<TypedQueueEntry> queue = queues.get(clazz);
        if (queue.isEmpty()) throw new NoSuchElementException();
        return (T) queue.dequeue().item;
    }

    public Object dequeue() throws Exception {
        long minCount = count;
        Queue<TypedQueueEntry> minQueue = null;

        for (Class clazz : queues.keySet()) {
            Queue<TypedQueueEntry> queue = queues.get(clazz);
            if(!queue.isEmpty() && queue.peek().entered < minCount) {
                minCount = queue.peek().entered;
                minQueue = queue;
            }
        }

        if (minQueue == null) throw new NoSuchElementException();

        return minQueue.dequeue().item;
    }
}

class AnimalShelterGeneric {
    TypedQueue tq = new TypedQueue(new Class[]{Cat.class, Dog.class});

    public void enqueue(Animal item) throws Exception {
        tq.enqueue(item);
    }

    public <T> T dequeue(Class<T> clazz) throws Exception {
        return tq.dequeue(clazz);
    }

    public Animal dequeue() throws Exception {
        return (Animal) tq.dequeue();
    }
}
