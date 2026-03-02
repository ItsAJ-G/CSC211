public class ChainHash {
    private Node[] table;
    private int m;

    private class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public ChainHash() {
        this.m=m;
        table=new Node[m+1];
    }

    public int hash(String key) {
        int h=key.hashCode();
        if (h<0) h=-h;
        return (h%m)+1;
    }

    public void insert(String key, String value) {
        int i=hash(key);
        Node head=table[i];
        Node current=head;

        while(current!=null){
            if(current.key.equals(key)){
                current.value=value;
                return;
            }
        }

    }
}
