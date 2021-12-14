package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.ExpressionException.LogicExpException;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class LogicExp implements IExp{
    IExp e1;
    IExp e2;
    int op; //1-and(&&) 2-or(||)

    public LogicExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() {
        switch (op) {
            case 1:
                return e1.toString() + "&&" + e2.toString();
            case 2:
                return e1.toString() + "||" + e2.toString();
        }
        return null;
    }

    @Override
    public IExp deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }


    @Override
    public IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {

            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {

                switch (this.op) {
                    case 1:
                        boolean res = ((BoolValue)v1).getVal() && ((BoolValue)v2).getVal();
                        return new BoolValue(res);
                    case 2:
                        boolean res2 = ((BoolValue)v1).getVal() || ((BoolValue)v2).getVal();
                        return new BoolValue(res2);
                }

            } else  throw new TypeException("not a bool");

        } else  throw new TypeException("not a bool");


        return v1;
    }
}
