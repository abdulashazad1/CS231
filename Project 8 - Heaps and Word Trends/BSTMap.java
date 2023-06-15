
/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

// Imports go here
import java.util.ArrayList;
import java.util.Comparator;

// Binary search tree class
public class BSTMap<K, V> implements MapSet<K, V> {
    // Root of the tree
    private TNode root;
    // Comparator
    private Comparator<K> comp;
    // Holds a list of the tree in pre-order sorting
    private ArrayList<TNode> nodeSet = new ArrayList<TNode>();

    // Constructor for the binary tree class
    public BSTMap(Comparator<K> Comp) {
        root = null;
        comp = Comp;
        nodeSet.clear();
    }

    // Adds a new keyvalue pair or replaces the value of an old key
    public V put(K k, V v) {
        if (root == null) {
            root = new TNode(k, v);
            return null;
        }
        if (containsKey(k) == true) {
            TNode currentNode = root;
            // Looping if currentNode != the node we're looking for
            while (comp.compare(currentNode.data.getKey(), k) != 0) {
                if (comp.compare(currentNode.data.getKey(), k) > 0) {
                    currentNode = currentNode.rightChild;
                }
                if (comp.compare(currentNode.data.getKey(), k) < 0) {
                    currentNode = currentNode.leftChild;
                }
            }
            V oldVal = currentNode.data.getValue();
            currentNode.data.setValue(v);
            return oldVal;
        } else {
            TNode currentNode = root;
            // Looping to find where to add the next node
            while (comp.compare(currentNode.data.getKey(), k) != 0) {
                if (comp.compare(currentNode.data.getKey(), k) > 0) {
                    if (currentNode.rightChild == null) {
                        currentNode.rightChild = new TNode(k, v);
                    }
                    currentNode = currentNode.rightChild;
                }
                if (comp.compare(currentNode.data.getKey(), k) < 0) {
                    if (currentNode.leftChild == null) {
                        currentNode.leftChild = new TNode(k, v);
                    }
                    currentNode = currentNode.leftChild;
                }
            }

            return null;
        }
    }

    // Looks for the key in the tree and returns its value
    public V get(K key) {
        if (root == null) {
            return null;
        } else {
            TNode currentNode = root;
            // Looping if currentNode != the node we're looking for
            while (comp.compare(currentNode.data.getKey(), key) != 0) {
                if (comp.compare(currentNode.data.getKey(), key) > 0) {
                    currentNode = currentNode.rightChild;
                }
                if (comp.compare(currentNode.data.getKey(), key) < 0) {
                    currentNode = currentNode.leftChild;
                }
            }

            // Returning the node if when we find it
            return currentNode.data.getValue();
        }
    }

    // Checks if the tree contains the key in the argument
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        } else {
            TNode currentNode = root;
            // Looping if currentNode != the node we're looking for
            while (comp.compare(currentNode.data.getKey(), key) != 0) {
                if (comp.compare(currentNode.data.getKey(), key) > 0) {
                    if (currentNode.rightChild == null) {
                        return false;
                    }
                    currentNode = currentNode.rightChild;
                }
                if (comp.compare(currentNode.data.getKey(), key) < 0) {
                    if (currentNode.leftChild == null) {
                        return false;
                    }
                    currentNode = currentNode.leftChild;
                }
            }

            return true;
        }
    }

    // Returns an array list with the keys of the tree in pre order
    @Override
    public ArrayList<K> keySet() {
        // Looping over the node arraylist from traverse
        ArrayList<K> keyset = new ArrayList<K>();
        nodeSet.clear();
        root.traverse(root);
        for (TNode n : nodeSet) {
            // Adding the key of the node to a new arraylist
            keyset.add(n.data.getKey());
        }
        // returning the new keyset arraylist
        return keyset;
    }

    // Returns an array list with the values of the keys in preorder
    @Override
    public ArrayList<V> values() {
        // An arraylist to store the values
        ArrayList<V> vals = new ArrayList<V>();
        // Clearing the node set from past traversals
        nodeSet.clear();
        // Traversing again
        root.traverse(root);

        // Looping over the nodeSet and adding
        // each val to vals
        for (TNode n : nodeSet) {
            vals.add(n.data.getValue());
        }
        // Returning the arraylist with the vals
        return vals;

    }

    // Returns an array list with the key value pairs in the tree
    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        // Arraylist to store the pairs
        ArrayList<KeyValuePair<K, V>> pairSet = new ArrayList<KeyValuePair<K, V>>();
        // Clearing out the node set from past traversals
        nodeSet.clear();
        // Traversing over the tree again
        root.traverse(root);

        // Adding each node's data to the list
        for (TNode n : nodeSet) {
            pairSet.add(n.data);
        }
        // Returning the list of pairs
        return pairSet;
    }

    // Returns the size of the tree
    @Override
    public int size() {
        // Dealing w an exception
        if (root == null) {
            return 0;
        } else {
            // Clearing out the node set from past traversals
            nodeSet.clear();
            // Traversing over the tree again
            root.traverse(root);
            // Returning the size of the nodeSet
            return nodeSet.size();
        }
    }

    // CLears out the tree
    @Override
    public void clear() {
        // Clearing out everything
        root = null;
    }

    // Private class for the node structure of the tree
    private class TNode {
        // Fields for the left and right childre
        TNode rightChild;
        TNode leftChild;
        // Field to hold the key value pair ion the node
        KeyValuePair<K, V> data;

        // Constructor for the TNode
        public TNode(K k, V v) {
            data = new KeyValuePair<K, V>(k, v);
            rightChild = null;
            leftChild = null;
        }

        // Traverses the tree in pre-order fashion
        public void traverse(TNode root) {
            TNode currentNode = root;
            if (currentNode == null) {
                return;
            }
            nodeSet.add(currentNode);
            traverse(currentNode.rightChild);
            traverse(currentNode.leftChild);
        }
    }

    // Main method to test the binary tree
    public static void main(String[] argv) {
        // create a BSTMap
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new AscendingString());
        // Adding values to the map
        bst.put("twenty", 20);
        bst.put("ten", 10);
        bst.put("eleven", 11);
        bst.put("five", 5);
        bst.put("six", 6);
        // Printing out the arraylist collections
        System.out.println(bst.keySet());
        System.out.println(bst.values());
        System.out.println(bst.entrySet());
        // Printing out specific nodes
        System.out.println(bst.get("eleven"));
        System.out.println(bst.get("twenty"));
        // Returning the size, clearing it, then returning the size
        System.out.println(bst.size());
        bst.clear();
        System.out.println(bst.size());

    }
}
