package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{

    private int val;

    public IntValue(int v) {
        this.val = v;
    }

    public int getVal() {
        return val;
    }

    public String valToString() {
        return Integer.toString(val);
    }

    @Override
    public IType getType() {
        return new IntType();
    }
}
