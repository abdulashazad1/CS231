/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/21/2022
 * Project 8
 */

/* 
My hash map is going to be using chaining as a
collision management system. The chain will be of 
linked lists. For the hash function, I will be u-
sing Java's default hash function.
*/

// Imports go here;
import java.util.ArrayList;
import java.util.Comparator;

public class Hashmap<K, V> implements MapSet<K, V> {
    // Fields for the hashmap.
    private LinkedList<KeyValuePair<K, V>>[] hashmap;
    private int size;
    private int numCollisions;
    private int CAPACITY = 31;
    private Comparator<K> comp;

    // Constructor 1 for the hashmap class.
    public Hashmap(Comparator<K> incomp) {
        this.hashmap = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            hashmap[i] = new LinkedList<KeyValuePair<K, V>>();
        }
        size = 0;
        numCollisions = 0;
        comp = incomp;
    }

    // Constructor 2 for the hashmap class.
    public Hashmap(Comparator<K> incomp, int capacity) {
        this.CAPACITY = capacity;
        this.hashmap = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            hashmap[i] = new LinkedList<KeyValuePair<K, V>>();
        }
        size = 0;
        numCollisions = 0;
        comp = incomp;
    }

    // Hash function.
    public int hash(K key) {
        // Hashing the key.
        int hashCode = key.hashCode();
        // Getting the absolute value of the hashcode.
        hashCode = Math.abs(hashCode);
        // Returning the hashcode.
        return hashCode;
    }

    // Compression function.
    public int compress(int hashCode) {
        // Modding the code by the size of the table.
        int index = hashCode % CAPACITY;
        // Returning the compressed code.
        return index;
    }

    // Getter for the Number of Collisions.
    public int getCollisions() {
        return numCollisions;
    }

    // Getter for CAPACITY.
    public int getCapacity() {
        return CAPACITY;
    }

    // Setter for CAPACITY.
    public void setCapacity(int newCapacity) {
        CAPACITY = newCapacity;
    }

    // Beginning implementing the MapSet interface.

    // Inserts or updates a key value pair.
    // On updating, returns V of old pair.
    // Otherwise returns null.
    @Override
    public V put(K new_key, V new_value) {
        // Checking if the key already exists
        if (containsKey(new_key)) { // If true
            // Getting the index
            int hashCode = hash(new_key);
            int index = compress(hashCode);
            // Using a comparator to find new_key in the linkedlist
            for (KeyValuePair<K, V> pair : hashmap[index]) {
                if (this.size > this.CAPACITY / 2) {
                    resize();
                }
                if (comp.compare(pair.getKey(), new_key) == 0) {
                    // Storing the old val of new_key
                    V oldVal = pair.getValue();
                    // Setting the val of new_key = new_value
                    pair.setValue(new_value);
                    // Returning the old val
                    return oldVal;
                }
            }

        } else { // if new_key wasn't already in the hashtable
            // Creating a Key Value Pair
            KeyValuePair<K, V> insert = new KeyValuePair<K, V>(new_key, new_value);
            // Hashing
            int hashCode = hash(new_key);
            int index = compress(hashCode);
            // Adding at the index
            hashmap[index].addFirst(insert);
            // +1 to the size
            size += 1;

            return null;
        }
        // Base case
        return null;
    }

    // Checks if the hash table contains a key
    @Override
    public boolean containsKey(K key) {
        // Getting a index to search the array
        int hashCode = hash(key);
        int index = compress(hashCode);
        if (hashmap[index].size() == 0) {
            return false;
        } else {
            // Using a comparator to see if the key is in the LL already
            for (KeyValuePair<K, V> pair : hashmap[index]) {
                if (comp.compare(pair.getKey(), key) == 0) {
                    if (hashmap[index].size() > 1) {
                        numCollisions += 1;
                    }
                    // Returning true if it is
                    return true;
                }
            }
        }
        // Otherwise returning false
        return false;
    }

    public void resize() {
        // Store the old hashmap somewhere
        ArrayList<KeyValuePair<K, V>> list = entrySet();
        // Change capacity
        CAPACITY = CAPACITY^2;
        // Make a new hashmap with the new capacity
        hashmap = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            hashmap[i] = new LinkedList<KeyValuePair<K, V>>();
        }
        // Allocate keys & values from the old hashmap to the new one
        for (KeyValuePair<K, V> pair : list) {
            put(pair.getKey(), pair.getValue());
        }
        numCollisions = 0;
    }

    // Returns the val of key. If key DNE,
    // returns null
    @Override
    public V get(K key) {
        // Checking to see if the key is in the hashtable
        if (containsKey(key) == false) {
            // Returning null if the key doesn't exist
            return null;
        } else {
            // Returning the val of the key in the hashtable

            // Getting a index to search the array
            int hashCode = hash(key);
            int index = compress(hashCode);

            // Using a comparator to see find the key
            for (KeyValuePair<K, V> pair : hashmap[index]) {
                if (comp.compare(pair.getKey(), key) == 0) {
                    // Returning true if it is
                    return pair.getValue();
                }
            }
        }
        // Base case
        return null;
    }

    @Override
    public ArrayList<K> keySet() {
        // An array list to store the keys.
        ArrayList<K> keySet = new ArrayList<K>();

        // Looping for the number of buckets in the map.
        for (int i = 0; i < CAPACITY; i++) {
            // Checking if the LL in the bucket is empty.
            if (hashmap[i].size() != 0) {
                // Getting each pair in the linked list.
                for (KeyValuePair<K, V> pair : hashmap[i]) {
                    // Adding the key of each pair to the arraylist.
                    keySet.add(pair.getKey());
                }
            }
        }
        // Returning the arraylist.
        return keySet;
    }

    @Override
    public ArrayList<V> values() {
        // An array list to store the values.
        ArrayList<V> valSet = new ArrayList<V>();

        // Looping for the number of buckets in the map.
        for (int i = 0; i < CAPACITY; i++) {
            // Checking if the LL in the bucket is empty.
            if (hashmap[i].size() != 0) {
                // Getting each pair in the linked list.
                for (KeyValuePair<K, V> pair : hashmap[i]) {
                    // Adding the value of each pair to the arraylist.
                    valSet.add(pair.getValue());
                }
            }
        }
        // Returning the arraylist.
        return valSet;
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        // An array list to store the Key-Val pairs
        ArrayList<KeyValuePair<K, V>> pairSet = new ArrayList<KeyValuePair<K, V>>();

        // Looping for the number of buckets in the map.
        for (int i = 0; i < CAPACITY; i++) {
            // Checking if the LL in the bucket is empty.
            if (hashmap[i].size() != 0) {
                // Getting each pair in the linked list.
                for (KeyValuePair<K, V> pair : hashmap[i]) {
                    // Adding each pair to the arraylist.
                    pairSet.add(pair);
                }
            }
        }
        // Returning the arraylist.
        return pairSet;
    }

    // Returns the size of the hashtable
    @Override
    public int size() {
        return size;
    }

    // Resetting everything besides the comparator
    @Override
    public void clear() {
        System.out.println("Number of Collisions: " + numCollisions);
        hashmap = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            hashmap[i] = new LinkedList<KeyValuePair<K, V>>();
        }
        size = 0;
        numCollisions = 0;
    }

    public static void main(String[] args) {
        AscendingString comp = new AscendingString();
        Hashmap<String, Integer> map = new Hashmap<>(comp);
        map.put("hat", 1);
        System.out.println(map.containsKey("hat"));
        System.out.println(map.entrySet());
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.getCapacity());
        map.resize();
        System.out.println(map.getCapacity());
        map.setCapacity(67);
        System.out.println(map.getCapacity());    
    }
}
