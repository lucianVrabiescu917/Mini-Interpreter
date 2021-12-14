package Tests.ExpressionTest;

import Model.ADT.IMyDict;
import Model.ADT.MyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.ADTException.MyDictException;
import Model.Exception.ExpressionException.VarExpException;
import Model.Exception.MyException;
import Model.Expression.IExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarExpTest {


    IValue val = new IntValue(5);
    IValue val2 = new BoolValue();
    IMyDict<String, IValue> tbl = new MyDict<String, IValue>();
    IMyDict<Integer, IValue> heap = new MyDictHeap<>();

    @BeforeEach
    void setUp() throws MyDictException {
        tbl.add("vInt", val);
        tbl.add("vBool", val2);
    }

    @Test
    void eval() throws MyException {
        IExp ve = new VarExp("vInt");
        IExp ve2 = new VarExp("vBool");
        IExp vErr = new VarExp("vErr");
        assertEquals( val, ve.eval(tbl, heap));
        assertEquals( val2, ve2.eval(tbl, heap));

        assertThrows(VarExpException.class , () -> {
            vErr.eval(tbl, heap);
        });
    }
}