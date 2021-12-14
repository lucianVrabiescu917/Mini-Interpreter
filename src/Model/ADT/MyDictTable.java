package Model.ADT;

import Model.Exception.ADTException.MyDictException;

public class MyDictTable<K, V> extends MyDict<K, V>{
    @Override
    public void add(K key, V value) throws MyDictException {
        if (isDefined(key))
            throw new MyDictException("key already inserted");
        dict.put(key, value);
    }
}
