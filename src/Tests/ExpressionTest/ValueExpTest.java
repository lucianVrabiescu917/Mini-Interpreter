package Tests.ExpressionTest;

import Model.ADT.IMyDict;
import Model.ADT.MyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.MyException;
import Model.Expression.ValueExp;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueExpTest {

    IValue val = new IntValue(5);
    ValueExp exp = new ValueExp(val);
    IValue val2 = new BoolValue();
    ValueExp exp2 = new ValueExp(val2);
    IMyDict<String, IValue> tbl = new MyDict<String, IValue>();
    IMyDict<Integer, IValue> heap = new MyDictHeap<>();

    @Test
    void eval() throws MyException {
        assertEquals( ((IntValue)val).getVal(), ((IntValue)exp.eval(tbl, heap)).getVal());
        assertEquals( ((BoolValue)val2).getVal(),((BoolValue)exp2.eval(tbl, heap)).getVal());
    }
}