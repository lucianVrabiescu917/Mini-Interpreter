package Model.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType {

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StringType);
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
}
