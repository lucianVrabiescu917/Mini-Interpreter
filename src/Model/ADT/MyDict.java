package Model.ADT;

import Model.Exception.MyDictException;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MyDict<K, V> implements IMyDict<K, V> {
    private Map<K, V> dict = new HashMap<K, V>();


    @Override
    public V getValue(K key) throws MyDictException {
        if (!dict.containsKey(key)) throw new MyDictException("key does not exist");
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

    @Override
    public boolean isDefined(K key) {
        return dict.containsKey(key);
    }


}
