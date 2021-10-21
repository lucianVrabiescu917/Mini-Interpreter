package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyDictException;
import Model.Exception.MyException;
import Model.Value.IValue;

public class VarExp implements IExp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl) throws MyException {
        try {
            IValue v = tbl.getValue(this.id);
            return v;
        } catch (MyDictException e) {
            throw new MyException(e.getMessage());
        }
    }
}
