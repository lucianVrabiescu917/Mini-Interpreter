package Model.ADT;

import Model.Exception.ADTException.MyStackException;

public interface IMyStack<T> {
    T pop() throws MyStackException;
    void push(T v);
    boolean isEmpty();
    int size();
    IMyStack<T> deepCopy();
    String toString();
    T peek();
}
