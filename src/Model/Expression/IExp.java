package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IMyDict<String,IValue> tbl) throws MyException;
}
