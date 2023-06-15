
/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<T> implements Iterable<T> {
    // Adding the node private class from last week
    private class Node {
        private Node next;
        private T data;
        private Node previous;

        // Constructor that initializes next = null and data = item
        public Node(T item) {
            next = null;
            previous = null;
            data = item;
        }

        // Getter for the value of the container field
        public T getThing() {
            return data;
        }

        // Sets next = n
        public void setNext(Node n) {
            next = n;
        }

        // Sets previous = n
        public void setPrevious(Node n) {
            previous = n;
        }

        // Returns next
        public Node getNext() {
            return next;
        }

        // Returns previous
        //public Node getPrevious() {  method to traverse the queue in reverse
        //    return previous;
        //}
    }

    // Fields for the queue class
    private int numItems;
    private Node head;
    private Node tail;
    private int maxSize = 10; // The max size of the queue, default = 10

    // Methods for the queue class

    // Constructor that initializes all fields: empty que
    public MyQueue() {
        clear();
    }

    // Empties the list by resetting the fields
    public void clear() {
        numItems = 0;
        head = null;
        tail = null;
    }

    // Returns the size of the queue
    public int size() {
        return numItems;
    }

    // Setter for the max size field
    public void setMax(int maximum) {
        maxSize = maximum;
    }

    // Returns the max possible size of the queue
    public int getMax() {
        return maxSize;
    }

    // Inserts an item and returns true; if not possible, returns false
    public boolean offer(T item) {
        // If the max size of the queue is exceeded, returns false
        if (numItems == maxSize) {
            return false;
        }
        // If head is null, adds item there
        Node current = head;
        if (current == null) {
            head = new Node(item);
            tail = new Node(item);
            numItems += 1;
            return true;
            // Else, add items at the end of the queue
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(item));
            tail = current.getNext();
            tail.setPrevious(current);
            numItems += 1;
            return true;
        }
    }

    // Returns but doesn't remove the head of the queue
    public T peek() {
        if (head == null){
            return null;
        }
        return head.getThing();
    }

    // Returns and removes the head of the queue
    public T poll() {
        // If head is null, returns null
        if (head == null) {
            return null;
            // Else, returns and removes head
        } else {
            Node current = head;
            head = head.getNext();
            numItems -= 1;
            return current.getThing();
        }
    }
    
    // Private class for Queue 
    private class QIterator implements Iterator<T>{
        Node current = null; // Current is null, behind head

        @Override
        public boolean hasNext() {
            if (current == null && head != null){
                return true;
            } else if (current != null && current.getNext() != null){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (current == null && head != null){
                current = head;
                return current.getThing();
            } else if (current != null){
                current = current.getNext();
                return current.getThing();
            }

            throw new NoSuchElementException();
        }
}

    @Override
    public Iterator<T> iterator() {
        return new QIterator();
    }

    // Main method to test class functions
    public static void main(String[] args) {
        // Creating a new queue wiht a max bound of 3
        MyQueue<Integer> que = new MyQueue<Integer>();
        que.setMax(3);

        for (int i = 0; i < 3; i++) {
            que.offer(i);
        }
        System.out.println("Initial Size: " + que.size());
        System.out.println("Peek: " + que.peek());
        System.out.println("Verifying successfull peek\nSize should be 3: " + que.size());
        System.out.println("Poll: " + que.poll());

        System.out.println("Verifying successful poll\nNew size: " + que.size());

        // Adding items to check implementation of max size constraint
        System.out.println("Should be true: " + que.offer(0));
        System.out.println("Should be false: " + que.offer(5));

        // Testing out the iterator
        for (Integer q: que){
            System.out.println("Val: " + q);
        }
        
    }
}