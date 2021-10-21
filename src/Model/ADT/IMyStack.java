package Model.ADT;

public interface IMyStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
}
