/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;

public class LinkedList<T> implements Iterable<T> { // Generic type specifier

    private class Node {
        private Node next;
        private T data;

        // Constructor that initializes next = null and data = item
        public Node(T item) {
            next = null;
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

        // Returns next
        public Node getNext() {
            return next;
        }
    }

    // Now making fields and methods for the linked lists class
    private int numItems; // Holds the number of items in the linked list
    Node head; // Points to the head of the linked list

    // Constructor that initializes all fields: empty list
    public LinkedList() {
        clear();
    }

    // Empties the list by resetting the fields
    public void clear() {
        numItems = 0;
        head = null;
    }

    // Returns the size of the list
    public int size() {
        return numItems;
    }

    // Adds an item at the beginning of the list
    public void addFirst(T item) {
        // If the head value = null, makes a head
        if (head == null) {
            head = new Node(item);
            numItems += 1;
            return;
        } // Else, makes a new head, connects it to the old head
          // and then assigns the head pointer to the new head
        else {
            Node newHead = new Node(item);
            newHead.next = head;
            head = newHead;
            numItems += 1;
            return;
        }
    }

    // Adds an item to the end of the list
    public void addLast(T item) {
        Node current = head;
        if (current == null) {
            head = new Node(item);
            numItems +=1;
        } else { // Iterating over the list until we're at the end of the list
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(item));
            numItems += 1;
        }
    }

    // Inserts an item at the specified index of the list
    public void add(int index, T item) {
        Node current = head;
        if (index == 0) {
            addFirst(item);
        }
        if (index == size() - 1) {
            addLast(item);
        } else {
            Node newNode = new Node(item);
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.next = current.getNext();
            current.next = newNode;
            numItems +=1;
        }
    }

    // Removes the element of the list at the index given
    public void remove(int index) {
        if (index == 0) {
            head = head.getNext();
            numItems -=1;
        } else {
            Node current = head; // Starting from the head

            // Looping over the list, until we get to the node before the target node
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node targetNode = current.getNext(); // Storing the target node
            if (targetNode.getNext() == null) {
                numItems -=1; 
                return;
            } else {
                numItems -=1; 
                current.setNext(targetNode.getNext()); // Assigning the node after the target node as the next node
            } // to our current node.
        } 
    }

    // Iterator priv class that handles traversing the list
    private class LLIterator implements Iterator<T> {
        Node current = null; // Current is behind head, the null reference things are starting from

        // Constructor for the iterator + head node reference
        public LLIterator(Node head) {
            return;
        }

        // Checks if there is another item in the list
        @Override
        public boolean hasNext() {
            // If current is null we're at the base of the linked list
            // Checks if head, the next value after a null current value, is null
            if (current == null && head != null) {
                return true; // Returns true if head is not null and current is
            } else if (current != null && current.getNext() != null) {
                return true; // Returns true if there is something after the current node
            }
            return false;
        }

        // Returns the next item in the list
        @Override
        public T next() {
            // Returns head if we're at the start of the list
            if (current == null && head != null) {
                current = head;
                return current.getThing();
            // Returns the item after next if it exists
            } else if (current != null) {
                current = current.getNext();
                return current.getThing();
            }
            // If none of those are true: throws an error
            throw new NoSuchElementException();
        }
    }

    // Returns a new LLIterator pointing to the head of the list
    @Override
    public Iterator<T> iterator() {
        return new LLIterator(head);
    }

    // Methods for making a linked ist into an array list
    public ArrayList<T> toArrayList(){
        // Initializing the arraylist to contain the linked list
        ArrayList<T> linkArray = new ArrayList<T>();
            Node current = head; // Start of the linked list
            linkArray.add(current.getThing());

            // While there are still nodes to traverse
            while (current.next != null){
                current = current.getNext();
                linkArray.add(current.getThing()); // Adds the value of the next node to the list
            }
            return linkArray; // Returning the linkedlist array
    }

    // Returns an arraylist of the items in the list but shuffled
    public ArrayList<T> toShuffledList(){
        // Initializing both array lists
        ArrayList<T> linkArray = new ArrayList<T>();
        ArrayList<T> shuffledLinkArray = new ArrayList<T>();
        // Random object, to generate random indexes for shuffling
        Random rand = new Random();
        // Stary of the nodes @ null, before head
        Node current = head;
        linkArray.add(current.getThing());

        // Adding node values to the list until there are no more nodes to traverse
            while (current.getNext() != null){
                current = current.getNext();
                linkArray.add(current.getThing());
            }

        // Shuffling using a for loop
        while (linkArray.isEmpty() == false){
            int random = rand.nextInt(linkArray.size());
            T removed = linkArray.remove(random);
            shuffledLinkArray.add(removed);
        }
        // Returning shuffled array
        return shuffledLinkArray;
    }
}
