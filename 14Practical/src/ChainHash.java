public class ChainHash {
    private Node[] table;
    private int m;
    private int size;

    private static class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public ChainHash(int m) {
        this.m = m;
        this.table = new Node[m];
        this.size = 0;
    }

    private int hash(String key) {
        int h = key.hashCode();
        // Improved hash function
        h = h ^ (h >>> 16);
        return (h & 0x7fffffff) % m;
    }

    public void insert(String key, String value) {
        if (key == null) return;

        int i = hash(key);
        Node current = table[i];

        // Check if key already exists
        while (current != null) {
            if (key.equals(current.key)) {
                current.value = value; // Update existing
                return;
            }
            current = current.next;
        }

        // Insert at beginning of chain
        Node newNode = new Node(key, value);
        newNode.next = table[i];
        table[i] = newNode;
        size++;
    }

    public String lookup(String key) {
        if (key == null) return null;

        int i = hash(key);
        Node current = table[i];

        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void delete(String key) {
        if (key == null) return;

        int i = hash(key);
        Node current = table[i];
        Node prev = null;

        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    table[i] = current.next; // Remove head
                } else {
                    prev.next = current.next; // Remove from middle/end
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public int chainLength(int index) {
        if (index < 0 || index >= m) return -1;

        int length = 0;
        Node current = table[index];
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
}