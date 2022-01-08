package Model.ADT;

import Model.Exception.ADTException.MyDictException;
import Model.Value.IValue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IMyDict<K, V> {
    V getValue(K key) throws MyDictException;

    int getSize();

    void add (K key, V value) throws MyDictException;

    boolean isDefined(K key);

    void removeKey(K key) throws MyDictException;

    String toString();

    Collection<V> getValues();

    Set<Map.Entry<K, V>> entrySet();

    MyDict<K, V> copy();

    Set<K> keySet();

    V get(K key);
}
