import java.util.*;

public class Main {
    static final int N = 1 << 20;  // 1,048,576
    static final int USE = 950000;
    static final int REPETITIONS = 30;
    static final int WARMUP = 5;

    static KeyValue[] data = new KeyValue[N];

    public static void main(String[] args) {
        generateData();

        // Warmup JVM
        warmup();

        double[] loadFactors = {0.75, 0.80, 0.85, 0.90, 0.95};
        System.out.println("Load Factor\tOpenHash(ms)\tChainHash(ms)");

        for (double alpha : loadFactors) {
            int m = (int) (USE / alpha);

            long openTime = timeOpenHash(m);
            long chainTime = timeChainHash(m);

            System.out.printf("%.2f\t\t%d\t\t%d%n", alpha, openTime, chainTime);
        }
    }

    static void generateData() {
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < N; i++) keys.add(i);
        Collections.shuffle(keys);

        for (int i = 0; i < N; i++) {
            data[i] = new KeyValue(
                    String.valueOf(keys.get(i)),
                    String.valueOf(i + 1)
            );
        }
    }

    static void warmup() {
        System.out.println("Warming up JVM...");
        for (int i = 0; i < WARMUP; i++) {
            OpenHash openTable = new OpenHash(USE);
            ChainHash chainTable = new ChainHash(USE);

            for (int j = 0; j < 10000; j++) {
                openTable.insert(data[j].key, data[j].value);
                chainTable.insert(data[j].key, data[j].value);
                openTable.lookup(data[j].key);
                chainTable.lookup(data[j].key);
            }
        }
        System.out.println("Warmup complete.\n");
    }

    static long timeOpenHash(int m) {
        long total = 0;

        for (int r = 0; r < REPETITIONS; r++) {
            OpenHash table = new OpenHash(m);

            // Insert phase (not timed)
            for (int i = 0; i < USE; i++) {
                table.insert(data[i].key, data[i].value);
            }

            // Lookup phase (timed)
            long start = System.nanoTime();
            for (int i = 0; i < USE; i++) {
                table.lookup(data[i].key);
            }
            long end = System.nanoTime();

            total += (end - start);
        }
        return (total / REPETITIONS) / 1_000_000; // Convert to ms
    }

    static long timeChainHash(int m) {
        long total = 0;

        for (int r = 0; r < REPETITIONS; r++) {
            ChainHash table = new ChainHash(m);

            // Insert phase (not timed)
            for (int i = 0; i < USE; i++) {
                table.insert(data[i].key, data[i].value);
            }

            // Lookup phase (timed)
            long start = System.nanoTime();
            for (int i = 0; i < USE; i++) {
                table.lookup(data[i].key);
            }
            long end = System.nanoTime();

            total += (end - start);
        }
        return (total / REPETITIONS) / 1_000_000; // Convert to ms
    }
}