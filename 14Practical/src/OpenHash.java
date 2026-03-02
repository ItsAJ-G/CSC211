public class OpenHash {
    private KeyValue[] table ;
    private int m;
    private int size;

    public OpenHash(int size) {
        this.m=m;
        table = new KeyValue[m+1];
        size=0;
    }

    public int hash(String key) {
        int h = key.hashCode();
        if (h < 0) h = -h;
        return (h % m) + 1;
    }


}
