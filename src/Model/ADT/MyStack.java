package Model.ADT;

import java.util.Stack;

public class MyStack<T> implements IMyStack<T> {
    private Stack<T> stack = new Stack<T>();

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }
}
