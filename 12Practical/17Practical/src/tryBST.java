//Nathi Gumede
//4533277


import java.util.*;

public class tryBST {

    //NODE CLASS (tNode)
    static class tNode {
        int key;
        tNode left, right;

        // Constructor WITH argument
        public tNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        // Constructor WITHOUT argument
        public tNode() {
            this.left = null;
            this.right = null;
        }
    }


    //BST CLASS

    static class BST {
        tNode root;

        public BST() {
            root = null;
        }


        //INSERT METHOD
        //Standard BST insertion


        public tNode insert(tNode node, int key) {
            if (node == null)
                return new tNode(key);

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);

            return node;
        }


        //BUILD PERFECT BST
        //Uses divide & conquer

        public void buildBalanced(int start, int end) {
            if (start > end) return;

            int mid = (start + end) / 2;

            // Insert middle first
            root = insert(root, mid);

            // Recursively build left and right halves
            buildBalanced(start, mid - 1);
            buildBalanced(mid + 1, end);
        }


        //CHECK IF VALID BST
        //Uses range constraints

        public boolean isBST() {
            return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBSTUtil(tNode node, int min, int max) {
            if (node == null)
                return true;

            // Violates BST property
            if (node.key <= min || node.key >= max)
                return false;

            return isBSTUtil(node.left, min, node.key) &&
                    isBSTUtil(node.right, node.key, max);
        }

    }
}