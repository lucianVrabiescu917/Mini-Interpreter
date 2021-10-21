package Model.ADT;

import Model.Exception.MyDictException;

public interface IMyDict<K, V> {
    V getValue(K key) throws MyDictException;
    int getSize();
    void addValue (K key, V value);
    boolean isDefined(K key);
}
