package Model.Type;

import Model.Type.IType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType {

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IntType);
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue();
    }
}
