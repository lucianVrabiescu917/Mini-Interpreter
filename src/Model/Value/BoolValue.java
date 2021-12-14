package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;

public class BoolValue implements IValue {

    private boolean val;

    public BoolValue(boolean v) {
        this.val = v;
    }

    public BoolValue() {
        this.val = false;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(val);
    }

    public boolean getVal() {
        return val;
    }

    public String toString() {
        return Boolean.toString(val);
    }
}
