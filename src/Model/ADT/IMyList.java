package Model.ADT;

public interface IMyList<T> {
    T getPoz(int i);
    void add(int i, T v);
    void add(T v);
    int getLen();
}
