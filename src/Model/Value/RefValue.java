package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

//    public IType getLocationType() {
//        return this.locationType;
//    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address, locationType);
    }
}
