//They were on my laptop for sometime cause i had issues
//Ended up restarting project

//Nathi Gumede
//4533277

import java.util.*;

public class BST {
    static class tNode {
        int key;
        tNode left;
        tNode right;

        //Constructor with arguement

        public tNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        //Constructor without arguement
        public tNode() {
            this.left = null;
            this.right = null;
        }

    }

    //BST CLass{
    tNode root;

    public BST() {
        root = null;
    }

    //Insert method
    //Standard BST insertion

    public tNode insert(tNode node, int key) {
        if (node == null) {
            return new tNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }
    //Build perfect bst
    //Uses devide and conquer
}
