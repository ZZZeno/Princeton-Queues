import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue <Item> implements Iterable<Item>  {
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private int size = 0;
    private int currentCapacity;
    private static final int INITIAL_CAPACITY = 16;
    private Item[] items;


    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
        currentCapacity = INITIAL_CAPACITY;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        items[size] = item;
        this.size += 1;
        this.allocateSpace();
    }

    public Item dequeue() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item ret = items[index];
        this.size -= 1;
        this.items[index] = null;
        this.recycleSpace(index);
        return ret;
    }

    public Item sample() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)+1];
    }

    private void allocateSpace() {
        if (this.size() > 8) {
            if (this.size() >= this.capacity()/2) {
                int newSize = this.capacity() * 2;
                Item[] newQueue = (Item[]) new Object[newSize];
                if (this.size() >= 0) System.arraycopy(this.items, 0, newQueue, 0, this.size());
                this.items = newQueue;
                this.currentCapacity = newSize;
            }
        }
    }

    private void recycleSpace(int recycledIndex) {
        if (this.size() < this.capacity()/4 && this.capacity() > 16) {
            int newSize = this.capacity() / 2;
            Item[] newQueue = (Item[]) new Object[newSize];
            for (int i = 0; i < this.size()+1; i ++) {
                if (this.items[i] != null) {
                    newQueue[i] = this.items[i];
                }
            }
            this.items = newQueue;
            this.currentCapacity = newSize;
        } else {
            for (int i = recycledIndex; i < this.size(); i ++) {
                this.items[i] = this.items[i+1];
            }
        }

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.currentCapacity;
    }


    private class DequeIterator implements Iterator<Item> {
        private Item[] randomItems = (Item[]) new Object[size()] ;
        int cursor = 0;

        public DequeIterator() {
            if (size() >= 0) System.arraycopy(items, 0, randomItems, 0, size());
            StdRandom.shuffle(randomItems);
        }

        public boolean hasNext() {
            return cursor < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomItems[cursor++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        System.out.println(queue.capacity());
        System.out.println(queue.dequeue());
        for (Integer temp : queue) {
            System.out.println(temp);
        }
    }
}
