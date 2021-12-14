package Tests.ADTTest;

import Model.ADT.IMyStack;
import Model.ADT.MyStack;
import Model.Exception.ADTException.MyDictException;
import Model.Exception.ADTException.MyStackException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    MyStack<Integer> stack = new MyStack<Integer>();

    @BeforeEach
    void setUp() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    void size() {
        assertEquals(3, stack.size());
    }

    @Test
    void pop() throws MyStackException {
        int s = stack.size();
        Integer e = stack.pop();
        assertEquals(3, e);
        assertEquals(2, stack.size());
        stack.pop(); stack.pop();

        assertThrows(MyStackException.class , () -> {
            stack.pop();
        });
    }

    @Test
    void push() {
        int s = stack.size();
        stack.push(4);
        assertEquals(4, stack.size());
    }

    @Test
    void isEmpty() throws MyStackException {
        stack.pop();stack.pop();stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void iterator() {
        int count = 1;
        for (Integer i : stack) {
            switch (count) {
                case 1:
                    assertEquals(i, 3);
                    break;

                case 2:
                    assertEquals(i, 2);
                    break;

                case 3:
                    assertEquals(i, 1);
                    break;
            }
            count++;
        }
    }

    @Test
    void copyIt() throws MyStackException {
        IMyStack<Integer> stk = new MyStack<Integer>();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        IMyStack<Integer> stkCpy = stk.deepCopy();
        assertEquals(4, stkCpy.pop());
        assertEquals(4, stk.pop());

    }
}