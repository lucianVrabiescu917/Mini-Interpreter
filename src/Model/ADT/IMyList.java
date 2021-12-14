package Model.ADT;

import Model.Exception.ADTException.MyListException;

public interface IMyList<T> {
    T get(int i) throws MyListException;
//    void add(int i, T v) throws MyListException;
    int getPos(T elem) throws MyListException;
    void add(T v);
    int getLen();
    void removePos(int i) throws MyListException;
    void remove(T elem) throws MyListException;
    String toString();
}
