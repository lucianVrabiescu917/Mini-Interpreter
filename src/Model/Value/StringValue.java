package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.StringType;

public class StringValue implements IValue{
    private String val;

    public StringValue(String v) {
        this.val = v;
    }

    public StringValue() {
        this.val = "";
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    public String getVal() {
        return val;
    }

    public String toString() {
        return val;
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(val);
    }
}
