import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue <Item> implements Iterable<Item>  {
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private final Node head = new Node(null);
    private final Node tail = new Node(null);
    private int size = 0;
    private static final int INITIAL_CAPACITY = 8;
    private Item[] items;


    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        items[size] = item;
        allocateSpace();
    }

    public Item dequeue() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size)+1;
        this.size -= 1;
        return items[index];
    }

    public Item sample() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)+1];
    }

    private void allocateSpace() {

    }

    private void recycleSpace() {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private class Node {
        Item item;
        Node next;
        Node before;

        Node(Item item) {
            item = item;
            next = null;
            before = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
//        private Node current;

        public DequeIterator() {
//            current = head.next;
        }

        public boolean hasNext() {
//            return current != null;
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Node node = new Node(null);
                return node.item;
            }
        }
    }
}
