package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithExp implements IExp {
    IExp e1;
    IExp e2;
    int op; //1-plus, 2-minus, 3-multiply, 4-divide

    public ArithExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(IMyDict<String, IValue> tbl) throws MyException {
        IValue v1, v2;

        v1 = (IValue) e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = (IValue) e2.eval(tbl);

            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int n1, n2;
                n1 = i1.getVal();
                n2 = i1.getVal();

                switch (this.op) {
                    case 1:
                        return new IntValue(n1 + n2);

                    case 2:
                        return new IntValue(n1 - n2);


                    case 3:
                        return new IntValue(n1 * n2);


                    case 4:
                        if (n2 == 0) throw new MyException("division by zero");
                        return new IntValue(n1 / n2);

                }
            } else throw new MyException("second operand is not an integer");

        } else throw new MyException("second operand is not an integer");


        return null;
    }
}
