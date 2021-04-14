package challenges.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void newListIsEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        assertTrue("new linked list is empty", l.isEmpty());
    }

    @Test
    public void insertMakesListNonEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(5);
        assertFalse("linked list with elements is not empty", l.isEmpty());
    }

    @Test
    public void sizeUpdatesOnInsertAndRemove () throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertEquals("size of empty list", 0, l.size());
        l.insert(1);
        assertEquals("size of list after insert", 1, l.size());
        l.append(2);
        assertEquals("size of list after append", 2, l.size());
        l.append(3);
        l.append(4);
        assertEquals("size of list after append", 4, l.size());
        l.insert(5);
        l.insert(6);
        assertEquals("size of list after append", 6, l.size());
        l.removeFromEnd();
        assertEquals("size of list after removeFromEnd", 5, l.size());
        l.pop();
        l.pop();
        assertEquals("size of list after pop", 3, l.size());
        l.append(5);
        l.removeFromEnd();
        l.removeFromEnd();
        l.removeFromEnd();
        l.pop();
        assertEquals("size of list after inserts and removes", 0, l.size());
    }

    @Test
    public void includesElementFalse () {
        LinkedList<Integer> l = new LinkedList<>();
        assertFalse("empty linked list includes no element",
                l.includes(5));
        l.insert(10);
        assertFalse("linked list .includes() should be false when element not in list",
                l.includes(5));
        l.insert(15);
        assertFalse("linked list .includes() should be false when element not in list",
                l.includes(5));
    }

    @Test
    public void includesElementTrue () {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(5);
        assertTrue(".includes() is true when element in list", l.includes(5));
        l.insert(10);
        assertTrue(".includes() is true when element in list", l.includes(10));
        assertTrue(".includes() is true when element in list", l.includes(5));
        l.insert(15);
        assertTrue(".includes() is true when element in list", l.includes(15));
        assertTrue(".includes() is true when element in list", l.includes(10));
        assertTrue(".includes() is true when element in list", l.includes(5));
    }

    @Test
    public void toStringOnEmpty() {
        LinkedList<Integer> l = new LinkedList<>();
        assertEquals(".toString() on empty list",
                "NULL", l.toString());
    }

    @Test
    public void toStringOnNonEmpty() {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".toString() on empty list",
                "{ 1 } -> { 2 } -> { 3 } -> NULL", l.toString());
    }

    @Test
    public void popOnEmptyThrows() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertThrows(".pop() on empty list throws",
                NoSuchElementException.class, l::pop);
        l.insert(5);
        l.pop();
        assertThrows(".pop() on empty list throws",
                NoSuchElementException.class, l::pop);
    }

    @Test
    public void popRetrievesElementsLIFO() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".pop() retrieves elements in last inserted first out order",
                1, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                2, (int) l.pop());
        l.insert(5);
        l.insert(6);
        assertEquals(".pop() retrieves elements in last inserted first out order",
                6, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                5, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                3, (int) l.pop());
    }

    @Test
    public void removeFromEndOnEmptyThrows() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertThrows(".removeFromEnd() on empty list throws",
                NoSuchElementException.class, l::removeFromEnd);
        l.insert(1);
        l.pop();
        assertThrows(".removeFromEnd() on empty list throws",
                NoSuchElementException.class, l::removeFromEnd);
    }

    @Test
    public void removeFromEndTest1() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                3, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                2, (int) l.removeFromEnd());
        l.insert(5);
        l.insert(6);
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                1, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                5, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                6, (int) l.removeFromEnd());
    }

    @Test
    public void insertIterables() {
        LinkedList<Integer> l = new LinkedList<>();
        Iterable<Integer> range = Arrays.asList(1, 2, 3, 4, 5);
        l.insert(range);
        for (int i = 5; i > 0; i--) {
            try {
                assertEquals("insert on iterable inserts elements in order",
                        i, (int) l.pop());
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail();
            }
        }
    }

    @Test
    public void appendIterables() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        Iterable<Integer> range = Arrays.asList(1, 2, 3, 4, 5);
        l.append(range);
        for (int i = 1; i < 6; i++) {
                assertEquals("append on iterable inserts elements in order",
                        i, (int) l.pop());
        }
    }

    @Test
    public void iterableInterface() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        List<Integer> range = Arrays.asList(test);
        for (Integer integer : test) l.append(integer);
        List<Integer> numbers = new ArrayList<>();
        int i = 0;
        for (Integer num : l) {
            assertEquals("iterable interface traverses list in order",
                    test[i], num);
            i++;
        }
    }

    @Test
    public void clearDeletesAllElements() {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        l.clear();
        assertTrue("list is empty after clear", l.isEmpty());
        assertEquals("size of list is 0 after clear", 0, l.size());
    }

    @Test
    public void getIndexInBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        l.append(Arrays.asList(test));
        for (int i = 0; i < test.length; i++) {
            assertEquals("get finds elements by index starting from 0",
                    test[i], l.get(i));
        }
    }

    @Test
    public void getIndexOutOfBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(-1));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(1));
        l.append(Arrays.asList(test));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(-1));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(7));
    }

    @Test
    public void getFromEndIndexOutOfBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        assertThrows("getFromEnd with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.getFromEnd(0));
        assertThrows("getFromEnd with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.getFromEnd(-1));
        assertThrows("getFromEnd with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.getFromEnd(1));
        l.append(Arrays.asList(test));
        assertThrows("getFromEnd with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.getFromEnd(-1));
        assertThrows("getFromEnd with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.getFromEnd(7));
    }

    @Test
    public void getFromEndIndexInBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        l.insert(Arrays.asList(test));
        for (int i = 0; i < test.length; i++) {
            assertEquals("getFromEnd finds elements by index starting from 0",
                    test[i], l.getFromEnd(i));
        }
    }

    @Test
    public void setIndexInBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        l.append(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < test.length; i++) l.set(i, test[i]);
        for (int i = 0; i < test.length; i++) {
            assertEquals(String.format("set() on index %d", i),
                    test[i], l.get(i));
        }
    }

    @Test
    public void setIndexOutOfBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(-1, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(0, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(1, 0));
        l.append(Arrays.asList(test));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(-1, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(7, 0));
    }

    @Test
    public void insertBeforeFirstOccurrenceItemNotInList() {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 5, 4, 6, 7));
        assertThrows("should throw when item not in list",
                NoSuchElementException.class, () -> l.insertBeforeFirstOccurrence(10, 10));
    }

    @Test
    public void insertBeforeFirstOccurrenceItemInList() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 2, 1));
        l.insertBeforeFirstOccurrence(2,100);
        assertTrue("inserts item into interior of list", l.includes(100));
        assertEquals("inserts item in correct position in interior",
                100, (int) l.get(2));
        assertEquals("increases size when inserting element", 6, l.size());
        l.insertBeforeFirstOccurrence(1, 1000);
        assertEquals("inserts item in correct position at beginning",
                1000, (int) l.get(0));
        assertEquals("increases size when inserting element", 7, l.size());
        assertEquals("inserts only before first occurrence", 1000, (int) l.pop());
        assertEquals("inserts only before first occurrence", 1, (int) l.pop());
        assertEquals("inserts only before first occurrence", 3, (int) l.pop());
        assertEquals("inserts only before first occurrence", 100, (int) l.pop());
        assertEquals("inserts only before first occurrence", 2, (int) l.pop());
        assertEquals("inserts only before first occurrence", 2, (int) l.pop());
        assertEquals("inserts only before first occurrence", 1, (int) l.pop());
        assertTrue(l.isEmpty());
    }

    @Test
    public void insertAfterFirstOccurrenceItemNotInList() {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 5, 4, 6, 7));
        assertThrows("should throw when item not in list",
                NoSuchElementException.class, () -> l.insertAfterFirstOccurrence(10, 10));
    }

    @Test
    public void insertAfterFirstOccurrenceItemInList() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 2, 5));
        l.insertAfterFirstOccurrence(3,100);
        assertTrue("inserts item into interior of list", l.includes(100));
        assertEquals("inserts item in correct position in interior",
                100, (int) l.get(2));
        assertEquals("increases size when inserting element", 6, l.size());
        l.insertAfterFirstOccurrence(5, 1000);
        assertEquals("inserts item in correct position at end",
                1000, (int) l.get(6));
        assertEquals("increases size when inserting element", 7, l.size());
        assertEquals("inserts only after first occurrence", 1, (int) l.pop());
        assertEquals("inserts only after first occurrence", 3, (int) l.pop());
        assertEquals("inserts only after first occurrence", 100, (int) l.pop());
        assertEquals("inserts only after first occurrence", 2, (int) l.pop());
        assertEquals("inserts only after first occurrence", 2, (int) l.pop());
        assertEquals("inserts only after first occurrence", 5, (int) l.pop());
        assertEquals("inserts only after first occurrence", 1000, (int) l.pop());
        assertTrue(l.isEmpty());
    }

    @Test
    public void deleteFirstOccurrenceItemNotInList() {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 5, 4, 6, 7));
        assertThrows("should throw when item not in list",
                NoSuchElementException.class, () -> l.deleteFirstOccurrence(10));
    }

    @Test
    public void deleteFirstOccurrenceItemInList() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 3, 2, 2, 5));
        l.deleteFirstOccurrence(2);
        assertEquals("decreases size when deleting element", 4, l.size());
        l.deleteFirstOccurrence(1);
        assertEquals("deletes item when it's the head", 3, (int) l.get(0));
        l.deleteFirstOccurrence(2);
        assertFalse("deletes both occurrences when run twice", l.includes(2));
        assertEquals("deletes only the items indicated", 3,(int) l.pop());
        assertEquals("deletes only the items indicated", 5,(int) l.pop());
        assertTrue(l.isEmpty());
    }

    @Test
    public void constructorUsingIterable() throws NoSuchElementException {
        LinkedList<Integer> empty = new LinkedList<>(Collections.emptyList());
        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(1, 2, 3));
        assertTrue("constructor using iterable on empty array gives empty list",
                empty.isEmpty());
        assertEquals("constructor using iterable populates list",
                1, (int) l.pop());
        assertEquals("constructor using iterable populates list",
                2, (int) l.pop());
        assertEquals("constructor using iterable populates list",
                3, (int) l.pop());
    }

    @Test
    public void equals() {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        l1.append(1);
        l1.append(2);
        l1.append(3);
        l2.append(1);
        l2.append(2);
        l2.append(3);
        assertTrue("equals is true on itself",
                l1.equals(l1));
        assertTrue("equals is true on equal lists",
                l1.equals(l2));
        assertTrue("equals is true on equal lists",
                l2.equals(l1));
    }

    @Test
    public void interleaveEmptyCases() {
        LinkedList<Integer> empty = new LinkedList<Integer>();
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));

        LinkedList<Integer> interleave1 = LinkedList.interleave(empty, empty);
        LinkedList<Integer> interleave2 = LinkedList.interleave(list, empty);
        LinkedList<Integer> interleave3 = LinkedList.interleave(empty, list);
        assertEquals("interleave on one or more empty linked lists give original list",
                empty, interleave1);
        assertEquals("interleave on one or more empty linked lists give original list",
            list, interleave2);
        assertEquals("interleave on one or more empty linked lists give original list",
                list, interleave3);
    }

    @Test
    public void interleaveNonEmptyCases() {
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(11, 22, 33));
        LinkedList<Integer> expected1 = new LinkedList<>(Arrays.asList(1,11,2,22,3,33,4,5));
        LinkedList<Integer> expected2 = new LinkedList<>(Arrays.asList(11,1,22,2,33,3,4,5));

        LinkedList<Integer> interleave1 = LinkedList.interleave(list1, list2);
        LinkedList<Integer> interleave2 = LinkedList.interleave(list2, list1);
        assertEquals("interleave on one or more empty linked lists give original list",
                expected1, interleave1 );
        assertEquals("interleave on one or more empty linked lists give original list",
                expected2, interleave2);
    }

    @Test
    public void map() {
        LinkedList<Integer> empty = new LinkedList<>();
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(2, 3, 5, 15, 12, 5));
        LinkedList<Integer> expected1 = new LinkedList<>(Arrays.asList(2, 4, 6, 8, 10));
        LinkedList<String> expected2 = new LinkedList<>(Arrays.asList("2", "Fizz", "Buzz", "FizzBuzz", "Fizz", "Buzz"));
        LinkedList<String> emptyString = new LinkedList<>();

        Function<Integer, String> fizzBuzz = n -> n % 15 == 0
                ? "FizzBuzz"
                : n % 5 == 0
                        ? "Buzz"
                        : n % 3 == 0 ? "Fizz" : "" + n;

        Function<Integer, Integer> timesTwo = n -> n * 2;

        assertEquals("map on empty list", empty.map(timesTwo), empty);
        assertEquals("map on empty list creates list of correct type", empty.map(fizzBuzz), emptyString);
        assertEquals("map", list1.map(timesTwo), expected1);
        assertEquals("map", list2.map(fizzBuzz), expected2);
    }

    @Test
    public void testMap() {
    }

    @Test
    public void forAll() {
        LinkedList<Integer> empty = new LinkedList<>();
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(2, 4, 6, 8, 10, 12));
        Predicate<Integer> isEven = n -> n % 2 == 0;

        assertTrue("empty set always satisfies forall", empty.forAll(isEven));
        assertFalse(list1.forAll(isEven));
        assertTrue(list2.forAll(isEven));
    }
}
