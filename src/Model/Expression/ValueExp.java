package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Value.IValue;

public class ValueExp implements IExp{
    IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }
    public String toString() {
        return e.toString();
    }

    @Override
    public IExp deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException {
        return e;
    }
}
