package Model.ADT;

import Model.Exception.ADTException.MyDictException;
import Model.Value.IValue;

import java.util.*;
import java.util.stream.Collectors;


public class MyDict<K, V> implements IMyDict<K, V> {
    protected Map<K, V> dict = new HashMap<K, V>();

    public MyDict(Map<K, V> dict) {
        this.dict = dict;
    }

    public MyDict() {}

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
    public void add(K key, V value) throws MyDictException {
        dict.put(key, value);
    }

    @Override
    public boolean isDefined(K key) {
        return dict.containsKey(key);
    }

    @Override
    public void removeKey(K key) throws MyDictException {
        if (!dict.containsKey(key))
            throw new MyDictException("key not stored");

        dict.remove(key);
    }

    @Override
    public String toString() {
        String res = " ";
        if (!(dict.keySet().isEmpty())) {
            for (Object key : dict.keySet().toArray()) {
                res += key.toString() + "->" + dict.get(key).toString() + "\n";
            }
        }
        return res;
    }

    @Override
    public Collection<V> getValues() {
        return this.dict.values();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dict.entrySet();
    }

    @Override
    public MyDict<K, V> copy() {
        return new MyDict<K, V>(dict.entrySet().
                stream().
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Override
    public Set<K> keySet() {
        return dict.keySet();
    }

    @Override
    public V get(K key) {
        return dict.get(key);
    }


}
