public class OpenHash {
    private KeyValue[] table;
    private int m;
    private int size;
    private static final KeyValue DELETED = new KeyValue("", ""); // Tombstone marker

    public OpenHash(int m) {
        this.m = m;
        this.table = new KeyValue[m];
        this.size = 0;
    }

    private int hash(String key) {
        int h = key.hashCode();
        // Improved hash function to prevent negative values and spread better
        h = h ^ (h >>> 16); // XOR high and low bits
        return (h & 0x7fffffff) % m; // Ensure non-negative
    }

    public boolean isFull() {
        return size >= m / 2; // Better to not let it get completely full
    }

    public void insert(String key, String value) {
        if (key == null) return;
        if (isFull()) return;

        int i = hash(key);
        int start = i;
        int firstDeleted = -1;

        // Find position to insert
        while (table[i] != null && table[i] != DELETED) {
            if (key.equals(table[i].key)) {
                table[i].value = value; // Update existing
                return;
            }
            i = (i + 1) % m;
            if (i == start) return; // Table is full
        }

        // Use first deleted slot if found
        table[i] = new KeyValue(key, value);
        size++;
    }

    public String lookup(String key) {
        if (key == null) return null;

        int i = hash(key);
        int start = i;

        while (table[i] != null) {
            if (table[i] != DELETED && key.equals(table[i].key)) {
                return table[i].value;
            }
            i = (i + 1) % m;
            if (i == start) break;
        }
        return null;
    }

    public void delete(String key) {
        if (key == null) return;

        int i = hash(key);
        int start = i;

        while (table[i] != null) {
            if (table[i] != DELETED && key.equals(table[i].key)) {
                table[i] = DELETED; // Mark as deleted
                size--;
                return;
            }
            i = (i + 1) % m;
            if (i == start) break;
        }
    }

    public int size() {
        return size;
    }
}