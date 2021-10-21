package Model.Type;

import Model.Type.IType;

public class IntType implements IType {

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IntType);
    }

    @Override
    public String toString() {
        return "int";
    }
}
