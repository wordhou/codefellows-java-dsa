package challenges.animalShelter;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AnimalShelterTest {

    @Test
    public void enqueueDequeueCatsDogs() {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new Cat("Moxie"));
        shelter.enqueue(new Dog("Tank"));
        shelter.enqueue(new Dog("Spike"));
        assertEquals("dequeue on animal type come out in FIFO for that type",
                "Moxie", shelter.dequeue("cat").getName());
        assertThrows("dequeue on animal type throws when no animals of that type",
                NoSuchElementException.class, () -> shelter.dequeue("cat"));
        shelter.enqueue(new Cat("Josie"));
        shelter.enqueue(new Cat("Jane"));
        assertEquals("dequeue on animal type come out in FIFO for that type",
                "Josie", shelter.dequeue("cat").getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                "Tank", shelter.dequeue("dog").getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                "Jane", shelter.dequeue("cat").getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                "Spike", shelter.dequeue("dog").getName());
        assertThrows("dequeue on animal type throws when no animals of that type",
                NoSuchElementException.class, () -> shelter.dequeue("dog"));
    }

    @Test
    public void dequeueOldest() {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new Cat("Moxie"));
        assertEquals("dequeue with no animal type comes out in FIFO on all animals",
                "Moxie", shelter.dequeue().getName());
        assertThrows("dequeue without animal type throws when no animals",
                NoSuchElementException.class, () -> shelter.dequeue());
        shelter.enqueue(new Dog("Tank"));
        shelter.enqueue(new Dog("Spike"));
        shelter.enqueue(new Cat("Josie"));
        shelter.enqueue(new Cat("Jane"));
        assertEquals("dequeue with no animal type comes out in FIFO on all animals",
                "Tank", shelter.dequeue().getName());
        assertEquals("dequeue with no animal type comes out in FIFO on all animals",
                "Spike", shelter.dequeue().getName());
        assertEquals("dequeue with no animal type comes out in FIFO on all animals",
                "Josie", shelter.dequeue().getName());
        assertEquals("dequeue with no animal type comes out in FIFO on all animals",
                "Jane", shelter.dequeue().getName());
        assertThrows("dequeue without animal type throws when no animals",
                NoSuchElementException.class, () -> shelter.dequeue());
    }

    @Test
    public void dequeueBadAnimalType() {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new Cat("Josie"));
        assertThrows("dequeue on non-existing animal type throws",
                NoSuchElementException.class, () -> shelter.dequeue("hedgehog"));
    }
}