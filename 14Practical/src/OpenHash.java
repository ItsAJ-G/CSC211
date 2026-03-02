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

    public boolean isFull() {
        return size==m;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isInTable(String key) {
        return lookup(key) != null;
    }

    public void insert(String key, String value) {
        if(isFull()){
            return;
        }
        int i = hash(key);
        int start = i;

        while (table[i].key.equals(key)) {
            table[i].value=value;
            return;
        }

        i=(i%m)+1;
        if(i==start){
            return;
        }

        table[i]=new KeyValue(key,value);
        size++;
    }

    public String lookup(String key) {
        int i = hash(key);
        int start = i;
        while (table[i]!=null) {
            if (table[i].key.equals(key)) {
                return table[i].value;
            }

        }
    }
}
