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
        /* -------------------------
          DELETE NODE (3 CASES)
          ------------------------- */
        public tNode delete(tNode root, int key) {
            if (root == null) return null;

            if (key < root.key)
                root.left = delete(root.left, key);
            else if (key > root.key)
                root.right = delete(root.right, key);
            else {
                // CASE 1: No child
                if (root.left == null && root.right == null)
                    return null;

                // CASE 2: One child
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;

                // CASE 3: Two children
                int minValue = findMin(root.right);
                root.key = minValue;
                root.right = delete(root.right, minValue);
            }

            return root;
        }

        /* -------------------------
           FIND MIN VALUE
           (used in deletion)
           ------------------------- */
        private int findMin(tNode node) {
            while (node.left != null)
                node = node.left;
            return node.key;
        }

        /* -------------------------
           REMOVE ALL EVEN NUMBERS
           ------------------------- */
        public tNode removeEvens(tNode node) {
            if (node == null) return null;

            // Process subtrees first
            node.left = removeEvens(node.left);
            node.right = removeEvens(node.right);

            // If even → delete
            if (node.key % 2 == 0)
                return delete(node, node.key);

            return node;
        }
    }

    /* =========================
       STATISTICS FUNCTIONS
       ========================= */

    // Compute average
    public static double average(long[] arr) {
        long sum = 0;
        for (long x : arr)
            sum += x;
        return (double) sum / arr.length;
    }

    // Compute standard deviation
    public static double stdDev(long[] arr, double mean) {
        double sum = 0;
        for (long x : arr)
            sum += Math.pow(x - mean, 2);
        return Math.sqrt(sum / arr.length);
    }

    /* =========================
       MAIN METHOD
       ========================= */
    public static void main(String[] args) {

        int repetitions = 30;

        // Adjust n until times > 1000ms
        int n = 15;

        int maxValue = (int) Math.pow(2, n) - 1;

        long[] populateTimes = new long[repetitions];
        long[] deleteTimes = new long[repetitions];

        for (int i = 0; i < repetitions; i++) {

            BST tree = new BST();

            /* -------- Populate Tree -------- */
            long start = System.nanoTime();

            tree.buildBalanced(1, maxValue);

            long end = System.nanoTime();
            populateTimes[i] = (end - start) / 1_000_000; // convert to ms

            /* -------- Verify BST -------- */
            if (!tree.isBST()) {
                System.out.println("ERROR: Tree is NOT a BST!");
                return;
            }

            /* -------- Remove Evens -------- */
            start = System.nanoTime();

            tree.root = tree.removeEvens(tree.root);

            end = System.nanoTime();
            deleteTimes[i] = (end - start) / 1_000_000;
        }

        //CALCULATE STATISTICS

        double avgPopulate = average(populateTimes);
        double stdPopulate = stdDev(populateTimes, avgPopulate);

        double avgDelete = average(deleteTimes);
        double stdDelete = stdDev(deleteTimes, avgDelete);


        //OUTPUT RESULTS


        System.out.println("\nMethod\t\t\tKeys\tAvg Time (ms)\tStd Dev");
        System.out.println("---------------------------------------------------------");

        System.out.printf("Populate Tree\t\t%d\t%.2f\t\t%.2f\n",
                maxValue, avgPopulate, stdPopulate);

        System.out.printf("Remove Evens\t\t%d\t%.2f\t\t%.2f\n",
                maxValue, avgDelete, stdDelete);
    }
}