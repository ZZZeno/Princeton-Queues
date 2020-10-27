import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node before;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.before = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Node node = current;
                current = current.next;
                return node.item;
            }
        }
    }

    private final Node head = new Node(null);
    private final Node tail = new Node(null);
    private int size = 0;

    public Deque() {
        this.head.item = null;
        this.tail.item = null;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node temp = new Node(item);
        if (this.isEmpty()) {
            this.head.next = temp;
        } else {
            Node realTail = this.tail.next;
            realTail.next = temp;
            temp.before = realTail;
        }
        this.tail.next = temp;
        this.size += 1;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node temp = new Node(item);
        if (this.isEmpty()) {
            this.tail.next = temp;
        } else {
            Node realHead = this.head.next;
            temp.next = realHead;
            realHead.before = temp;
        }
        this.head.next = temp;
        this.size += 1;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node head = this.head.next;
        if (this.head.next == this.tail.next) {
            this.size -= 1;
            this.head.next = null;
            this.tail.next = null;
            return head.item;
        } else {
//            Item ret = this.head.next.item;
            Node newHead = head.next;
            newHead.before = null;
            head.next = null;
            this.head.next = newHead;
            this.size -= 1;
            return head.item;
        }
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node tail = this.tail.next;
        if (this.head.next == this.tail.next) {
            this.size -= 1;
            this.head.next = null;
            this.tail.next = null;
            return tail.item;
        } else {
//            Item ret = this.tail.next.item;
            Node newTail = tail.before;
            tail.before = null;
            newTail.next = null;
            this.tail.next = newTail;
            this.size -= 1;
            return tail.item;
        }
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> deq2 = new Deque<Integer>();

        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

        deq2.addFirst(1);
        deq2.addFirst(2);
        deq2.addFirst(3);
        deq2.addFirst(4);
        deq2.addFirst(5);


        System.out.println("deq2: " + deq2.toString());

        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.removeFirst();
        deq2.removeFirst();
        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

        deq2.removeLast();
        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(1);
        deq2.addLast(2);
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(3);
        deq2.addLast(4);
        System.out.println("deq2: " + deq2.toString());

        System.out.println("size: " + deq2.size());


        Iterator itr = deq2.iterator();

        //System.out.println(itr.);
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        //System.out.println(itr.next());
    }
}
