package edu.greenriver.sdev333;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    //field
    private Node root;

    //helper class
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N; //number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;

        }
    }
    @Override
    public void put(KeyType key, ValueType value) {

    }

    @Override
    /*public ValueType get(KeyType key) {
        //given a key, find the value
        //set root to be current, so we have a starting point
        Node current = root;
        //keep going down the tree as long as not null
        while (current != null) {
            ///to choose whether to go left or right use the compareTo method returns neg, zero, or pos #
            //keys are not primitive they are class objects so use the .compareTo
            int comp = key.compareTo(current.key);
            //so if the comparison is less than we go left and so on
            if (comp < 0) {
                current = current.left;
            }
            else if (comp > 0) {
                current = current.right;
            }
            else{
                return current.val;
            }
        }//end of while loop
        //if not there
        return null;
    }

     */

    public ValueType get(KeyType key) {
        return get(root, key);
    }

    //recursive way
    //helper method
    private ValueType get(Node current, KeyType key) {
        if (current == null) {
            return null;
        }

        int comp = key.compareTo(current.key);

        if (comp < 0) {
            return get(current.left, key);
        }
        else if (comp > 0) {
            return get(current.right, key);
        }
        else {
            return current.val;
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        else {
            return root.N;
        }
    }

    //helper method for being flexible looking at subtree size given the current spot


    @Override
    public KeyType min() {
        return null;
    }

    @Override
    public KeyType max() {
        return null;
    }

    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

    @Override
    public int rank(KeyType key) {
        return 0;
    }

    @Override
    public KeyType select(int k) {
        return null;
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
