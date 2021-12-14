package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{

    private int val;

    public IntValue(int v) {
        this.val = v;
    }

    public IntValue() {
        this.val = 0;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(val);
    }
}
