package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;

public class BoolValue implements IValue {

    private boolean val;

    public BoolValue(boolean v) {
        this.val = v;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getVal() {
        return val;
    }

    String valToString() {
        return Boolean.toString(val);
    }
}
