package Model.ADT;

public interface IMyDict<K, V> {
    V getValue(K key);
    int getSize();
    void addValue (K key, V value);
}
