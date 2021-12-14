package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Value.IValue;

public interface IExp {
    String toString();
    IExp deepCopy();
    IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException;
}
