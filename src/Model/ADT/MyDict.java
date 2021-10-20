package Model.ADT;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MyDict<K, V> implements IMyDict<K, V> {
    private Map<K, V> dict = new HashMap<K, V>();


    @Override
    public V getValue(K key) {
        return dict.get(key);
    }

    @Override
    public int getSize() {
        return dict.size();
    }

    @Override
    public void addValue(K key, V value) {
        dict.put(key, value);
    }
}
