package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.DivisionByZeroException;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelExp implements IExp{
    IExp e1;
    IExp e2;
    String rel;

    public RelExp(IExp e1, IExp e2, String rel) {
        this.e1 = e1;
        this.e2 = e2;
        this.rel = rel;
    }

    public String toString() {
        return e1.toString() + rel + e2.toString();
    }

    @Override
    public IExp deepCopy() {
        return new RelExp(e1, e2, rel);
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl, IMyDict<Integer, IValue> heap) throws MyException {
        IValue v1, v2;

        v1 = (IValue) e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = (IValue) e2.eval(tbl, heap);

            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();

                switch (rel) {
                    case "<":
                        return new BoolValue(n1 < n2);

                    case "<=":
                        return new BoolValue(n1 <= n2);


                    case "==":
                        return new BoolValue(n1 == n2);

                    case "!=":
                        return new BoolValue(n1 != n2);

                    case ">":
                        return new BoolValue(n1 > n2);

                    case ">=":
                        return new BoolValue(n1 >= n2);

                }
            } else throw new TypeException("second operand is not an integer");

        } else throw new TypeException("second operand is not an integer");


        return v1;
    }

    @Override
    public IType typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
