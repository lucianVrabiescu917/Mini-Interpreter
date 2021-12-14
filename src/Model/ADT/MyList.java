package Model.ADT;

import Model.Exception.ADTException.MyListException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IMyList<T> {
    private List<T> list = new ArrayList<T>(10);



    @Override
    public T get(int i) throws MyListException {
        if (i < 0 || i >= list.size())
            throw new MyListException("index out of bounds");

        return list.get(i);
    }

    @Override
    public int getPos(T elem) throws MyListException {
        if (!list.contains(elem)) {
            throw new MyListException("element not stored");
        }
        return list.indexOf(elem);
    }

//    @Override
//    public void add(int i, T v) throws MyListException {
//        if (i < 0 || i >= list.size())
//            throw new MyListException("index out of bounds");
//        list.add(i, v);
//    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public int getLen() {
        return list.size();
    }

    @Override
    public void removePos(int i) throws MyListException {
        if (i < 0 || i >= list.size())
            throw new MyListException("index out of bounds");
        list.remove(i);
    }

    @Override
    public void remove(T elem) throws MyListException {
           if (!list.contains(elem)) {
               throw new MyListException("element not stored");
           }
           list.remove(elem);
    }

    @Override
    public String toString() {
        String res = "";
        for (T elem : list) {
            res += elem.toString() + "\n";
        }
        return res;
    }


}
