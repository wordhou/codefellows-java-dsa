package challenges.animalShelter;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AnimalShelterGenericTest {
    @Test
    public void enqueueDequeueCatsDogs() throws Exception {
        AnimalShelterGeneric shelter = new AnimalShelterGeneric();
        shelter.enqueue(new Cat("Moxie"));
        shelter.enqueue(new Dog("Tank"));
        shelter.enqueue(new Dog("Spike"));
        assertEquals("dequeue on animal type come out in FIFO for that type",
                             "Moxie", shelter.dequeue(Cat.class).getName());
        assertThrows("dequeue on animal type throws when no animals of that type",
                     NoSuchElementException .class, () -> shelter.dequeue(Cat.class));
            shelter.enqueue(new Cat("Josie"));
            shelter.enqueue(new Cat("Jane"));
        assertEquals("dequeue on animal type come out in FIFO for that type",
                             "Josie", shelter.dequeue(Cat.class).getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                             "Tank", shelter.dequeue(Dog.class).getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                             "Jane", shelter.dequeue(Cat.class).getName());
        assertEquals("dequeue on animal type come out in FIFO for that type",
                             "Spike", shelter.dequeue(Dog.class).getName());
        assertThrows("dequeue on animal type throws when no animals of that type",
                     NoSuchElementException.class, () -> shelter.dequeue(Cat.class));
    }

    @Test
    public void dequeueOldest() throws Exception {
        AnimalShelterGeneric shelter = new AnimalShelterGeneric();
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
    public void dequeueBadAnimalType() throws Exception {
        AnimalShelterGeneric shelter = new AnimalShelterGeneric();
        shelter.enqueue(new Cat("Josie"));
        assertThrows("dequeue on non-existing animal type throws",
                Exception.class, () -> shelter.dequeue(Hedgehog.class));
    }
}

class Hedgehog extends Animal {
    public Hedgehog(String name) {
        super(name);
    }
}