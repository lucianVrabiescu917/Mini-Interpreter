package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Value.IValue;

public class ValueExp implements IExp{
    IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl) throws MyException {
        return e;
    }
}
