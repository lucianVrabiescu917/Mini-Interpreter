package Tests.ExpressionTest;

import Model.ADT.IMyDict;
import Model.ADT.MyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.ExpressionException.LogicExpException;
import Model.Exception.ExpressionException.VarExpException;
import Model.Exception.MyException;
import Model.Expression.IExp;
import Model.Expression.LogicExp;
import Model.Expression.ValueExp;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExpTest {

    IExp eT = new ValueExp(new BoolValue(true));
    IExp eF = new ValueExp(new BoolValue(false));
    IExp err = new ValueExp(new IntValue(3));
    IMyDict<String, IValue> tbl = new MyDict<String, IValue>();
    LogicExp le1 = new LogicExp(eT, eF, 2);
    LogicExp le2 = new LogicExp(le1, eF, 1);
    IMyDict<Integer, IValue> heap = new MyDictHeap<>();

    @BeforeEach
    void setUp(){

    }

    @Test
    void eval() throws MyException {
        assertTrue(((BoolValue)le1.eval(tbl, heap)).getVal());
        assertFalse(((BoolValue)le2.eval(tbl, heap)).getVal());

        assertThrows(LogicExpException.class , () -> {
            LogicExp lerr = new LogicExp(eT, err, 2);
            lerr.eval(tbl, heap);
        });

        assertThrows(LogicExpException.class , () -> {
            LogicExp lerr2 = new LogicExp(err, eF, 2);
            lerr2.eval(tbl, heap);
        });

    }
}