package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class RHeapExp implements IExp{
    IExp exp;

    public RHeapExp(IExp exp) {
        this.exp = exp;
    }

    @Override
    public IExp deepCopy() {
        return new RHeapExp(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException {
        IValue val = exp.eval(tbl, heap);

        if (!(val.getType() instanceof RefType))
            throw new TypeException("not a ref");

        int address = ((RefValue)val).getAddress();

        if (!heap.isDefined(address))
            throw new VarException("address not stored");

        return heap.getValue(address);
    }
}
