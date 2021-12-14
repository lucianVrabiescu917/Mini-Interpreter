package Model.ADT;
import java.util.Map;

import static java.lang.Integer.valueOf;
public class MyDictHeap<K, V> extends MyDict<K, V> {
    K next;
    Integer count;

    public MyDictHeap() {
        next = (K) new Integer(1);
        count = 1;
    }

    public void add(V val) {
        dict.put(next, val);
        count++;
        next = (K) count;
    }

    public void change(K key, V val) {
        dict.put(key, val);
    }

    public int getNext() {
        return count;
    }

    public void setContent(Map<K, V> content) {
        this.dict = content;
    }
}
