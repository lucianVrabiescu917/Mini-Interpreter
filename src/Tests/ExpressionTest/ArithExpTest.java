package Tests.ExpressionTest;

import Model.ADT.IMyDict;
import Model.ADT.MyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.ExpressionException.ArithExpException;
import Model.Exception.ExpressionException.LogicExpException;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.IExp;
import Model.Expression.ValueExp;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithExpTest {

    IExp i5 = new ValueExp(new IntValue(5));
    IExp i3 = new ValueExp(new IntValue(3));
    IExp i0 = new ValueExp(new IntValue(0));
    IExp ierr = new ValueExp(new BoolValue());
    IExp ae1 = new ArithExp(i5, i3, 1);
    IExp ae2 = new ArithExp(i3, i5, 2);
    IExp ae3 = new ArithExp(i0, i5, 3);
    IExp ae4 = new ArithExp(i5, i3, 4);
    IExp aerr = new ArithExp(i3, i0, 4);
    IExp aerr2 = new ArithExp(ierr, i5, 2);
    IMyDict<String, IValue> tbl = new MyDict<String, IValue>();
    IMyDict<Integer, IValue> heap = new MyDictHeap<>();


    @Test
    void eval() throws MyException {

        assertEquals(8,((IntValue)ae1.eval(tbl, heap)).getVal());
        assertEquals(-2,((IntValue)ae2.eval(tbl, heap)).getVal());
        assertEquals(0,((IntValue)ae3.eval(tbl, heap)).getVal());
        assertEquals(1,((IntValue)ae4.eval(tbl, heap)).getVal());

        assertThrows(ArithExpException.class , () -> {
            aerr.eval(tbl, heap);
        });

        assertThrows(ArithExpException.class , () -> {
            aerr2.eval(tbl, heap);
        });

    }
}