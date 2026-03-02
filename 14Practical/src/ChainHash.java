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
            current=current.next;
        }
        Node newNode=new Node(key,value);

        if(head==null){
            table[i]=newNode;
        }
        else{
            current=head;
            while(current.next!=null){
                current=current.next;
                current.next=newNode;
            }
        }
    }

    public String lookup(String key){
        int i=hash(key);
        Node current=table[i];

        while(current!=null){
            if(current.key.equals(key)){
                return current.value;
            }
            current=current.next;
        }
        return null;
    }

    public boolean IsInTable(String key){
        return (lookup(key)!=null);
    }

    public String remove(String key){
        int i=hash(key);
        Node current=table[i];
        Node prev=null;
        while(current!=null){
            if(current.key.equals(key)){
                if(prev==null){
                    table[i]=current.next;
                }
                else{
                    prev.next=current.next;
                }
                return current.value;
            }
            prev=current;
            current=current.next;
        }
        return null;
    }
}
