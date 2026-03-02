public class KeyValue {
    String key;
    String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Added equals and hashCode for better object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        KeyValue that = (KeyValue) obj;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "KeyValue{key='" + key + "', value='" + value + "'}";
    }
}