/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

import java.util.Comparator;

public class PQHeap<T>{
    // Fields go here
    Object[] heap;
    int heapSize;
    int heapCapacity;
    Comparator<T> cmp;
    
    // Constructor for the Heap
    public PQHeap(Comparator<T> comparator){
        this.heapSize = 0;
        this.heapCapacity = 50;
        this.heap = new Object[heapCapacity];
        this.cmp = comparator;
    }

    // Sets the capacity for the heap
    public void setCapacity(int newCapacity){
        // Storing the new capacity int
        this.heapCapacity = newCapacity;
        // Making a new array of new capacity
        Object[] newHeap = new Object[heapCapacity];
        // Populating it with items from the old heap array
        for (int i=0; i<size(); i++){
            newHeap[i] = heap[i];
        }
        this.heap = newHeap;
    }

    // Adds to heap, increments size
    public void add(T obj){
        // Check if array has space
        if (size() < heapCapacity){
            // If yes then add obj
            heap[size()] = obj;
            this.heapSize += 1;
            bubbleUp(size()-1);
        } else {
            // If not then increase capacity
            setCapacity(heapCapacity*2);
            // And then add obj
            heap[size()] = obj;
            bubbleUp(size());
            this.heapSize += 1;
        }
    }

    // Removes root, decrements size
    public void remove(){
        heap[0] = heap[size() - 1];
        bubbleDown(0);
        heapSize -= 1;
    }

    // Returns the capacity of the heap
    public int getCapacity(){
        return this.heapCapacity;
    }

    // Returns the number of elements in the heap
    public int size(){
        return this.heapSize;
    }

    // Given an index returns the index of the parent node
    public int parentIndex(int index){
        return (index-1)/2;
    }

    // Given an index returns the index of the left child node
    public int leftChild(int index){
        return (2*index)+1;
    }

    // Given an index returns the index of the right child node
    public int rightChild(int index){
        return (2*index)+2;
    }

    // Gets the value in the object array
    public T getVal(int index){
        return (T) heap[index];
    }

    // Clear method resets the heap
    public void clear(){
        this.heapSize = 0;
        this.heapCapacity = 50;
        this.heap = new Object[heapCapacity];
    }
    /*
    Methods to handle the reheaping process
    */

    // Swaps the inserted node w the parent node 
    // While parent node < inserted node
    public void bubbleUp(int index){
        int newEntry = index;
        // While the parent > child
        while (parentIndex(newEntry) >= 0 && cmp.compare(getVal(newEntry), getVal(parentIndex(newEntry))) > 0){
            // Perform a swap
            T swap = getVal(parentIndex(newEntry));
            heap[parentIndex(newEntry)] = heap[newEntry];
            heap[newEntry] = swap; 
            newEntry = parentIndex(newEntry);
        }
    }

    // Swaps the new root with the child node 
    // While child node > inserted node
    public void bubbleDown(int index){
        int newRoot = index;
        // While new root < children
        while ((leftChild(newRoot) <= size()-1 || rightChild(newRoot) <= size()-1) && (cmp.compare(getVal(newRoot), getVal(leftChild(newRoot))) < 0 || cmp.compare(getVal(newRoot), getVal(rightChild(newRoot))) < 0 )){
            
            if (rightChild(newRoot) > size()-1){
                if (cmp.compare(getVal(newRoot), getVal(leftChild(newRoot))) < 0){
                    // Perform a swap with the left child
                    T swap = getVal(newRoot);
                    heap[newRoot] = heap[leftChild(newRoot)];
                    heap[leftChild(newRoot)] = swap;
                    newRoot = leftChild(newRoot); 
            }
        }

            if (cmp.compare(getVal(rightChild(newRoot)), getVal(leftChild(newRoot))) > 0){
                // Perform swap with right child
                T swap = getVal(newRoot);
                    heap[newRoot] = heap[rightChild(newRoot)];
                    heap[rightChild(newRoot)] = swap;
                    newRoot = rightChild(newRoot); 

            } else if (cmp.compare(getVal(leftChild(newRoot)), getVal(rightChild(newRoot))) < 0){
                // Perform swap with the left child
                T swap = getVal(newRoot);
                    heap[newRoot] = heap[leftChild(newRoot)];
                    heap[leftChild(newRoot)] = swap;
                    newRoot = leftChild(newRoot); 

            } else {
                // Perform swap with the left child bc complete tree
                T swap = getVal(newRoot);
                    heap[newRoot] = heap[leftChild(newRoot)];
                    heap[leftChild(newRoot)] = swap;
                    newRoot = leftChild(newRoot);
            }
    }
    }

    // Main function to run code
    public static void main(String[] args) {

        AscendingInt cmp = new AscendingInt();
        /*Integer arr[] = new Integer[5];
        for (int i=1; i<4; i++){
            arr[i] = 100/(i+1);
        }

        PQHeap<Integer> myHeap = new PQHeap<>(cmp);

        myHeap.heap = arr;
        myHeap.heapSize = 5;

        System.out.print("  ");
        for (int i=0; i<5; i++){
            System.out.print(arr[i] + " ");
        }

        myHeap.bubbleDown(0);

        System.out.print("  ");
        for (int i=0; i<5; i++){
            System.out.print(arr[i] + " "); 
        } */
        PQHeap<Integer> myHeap = new PQHeap<>(cmp);
        myHeap.add(100);
        myHeap.add(70);
        myHeap.add(90);
        myHeap.add(120);
        myHeap.add(150);
        myHeap.remove();

        for (int i=0;i<myHeap.size(); i++){
            System.out.print(myHeap.heap[i] + " ");
        }
    }   
}