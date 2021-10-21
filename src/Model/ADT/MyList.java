package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IMyList<T> {
    private List<T> list = new ArrayList<T>();

    @Override
    public T getPoz(int i) {
        return list.get(i);
    }

    @Override
    public void add(int i, T v) {
        list.add(i, v);
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public int getLen() {
        return list.size();
    }










}
