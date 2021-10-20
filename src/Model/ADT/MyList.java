package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IMyList<T> {
    private List<T> list = new ArrayList<T>();

    public T getPoz(int i) {
        return list.get(i);
    }

    public void add(int i, T v) {
        list.add(i, v);
    }

    public void add(T v) {
        list.add(v);
    }

    public int getLen() {
        return list.size();
    }










}
