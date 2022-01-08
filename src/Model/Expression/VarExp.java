package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.ADTException.MyDictException;
import Model.Exception.ExpressionException.VarExpException;
import Model.Exception.MyException;
import Model.Type.IType;
import Model.Value.IValue;

public class VarExp implements IExp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    @Override
    public IExp deepCopy() {
        return new VarExp(id);
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException {
        try {
            IValue v = tbl.getValue(this.id);
            return v;
        } catch (MyDictException e) {
            throw new VarExpException(e.getMessage());
        }
    }

    @Override
    public IType typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        return typeEnv.getValue(id);
    }
}
