package Model.Type;

public class BoolType implements IType{

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof BoolType);
    }

    @Override
    public String toString() {
        return "bool";
    }


}
