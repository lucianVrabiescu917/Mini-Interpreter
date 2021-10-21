package Model.ADT;

import Model.Expression.IExp;

import java.util.Iterator;
import java.util.Stack;

public class MyStack<T> implements IMyStack<T>, Iterable<T> {
    private Stack<T> stack = new Stack<T>();

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyStackIterator();
    }

    class MyStackIterator implements Iterator<T> {
        private int index = stack.size() - 1;

        public boolean hasNext() {
            return index >= 0;
        }

        public T next() {
            return stack.get(index--);
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported yet");
        }
    }




//    public MyStack<T> copy() {
//        MyStack<T> stkC = new MyStack<T>();
//        MyStack<T> aux = new MyStack<T>();
//        while (!this.isEmpty()) {
//            aux.push(this.pop());
//        }
//
//        while (!aux.isEmpty()) {
//            T elem = aux.pop();
//            this.push(elem);
//            stkC.push(new T(elem));
//        }
//
//    }




}
