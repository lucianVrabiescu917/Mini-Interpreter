package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class LogicExp implements IExp{
    IExp e1;
    IExp e2;
    int op; //1-or 2-and

    public LogicExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }


    @Override
    public IValue eval(IMyDict<String, IValue> tbl) throws MyException {
        IValue v1, v2;
        v1 = (IValue) e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {

            v2 = (IValue) e2.eval(tbl);
            if (v2.getType().equals(new BoolType())) {

                switch (this.op) {
                    case 1:
                        boolean res = ((BoolValue)v1).getVal() && ((BoolValue)v2).getVal();
                        return new BoolValue(res);
                    case 2:
                        boolean res2 = ((BoolValue)v1).getVal() || ((BoolValue)v2).getVal();
                        return new BoolValue(res2);
                }

            } else  throw new MyException("not a bool");

        } else  throw new MyException("not a bool");


        return null;
    }
}
