package Model.Expression;

import Model.ADT.IMyDict;
import Model.Exception.DivisionByZeroException;
import Model.Exception.ExpressionException.ArithExpException;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

import java.util.ArrayList;
import java.util.List;

public class ArithExp implements IExp {
    IExp e1;
    IExp e2;
    int op; //1-plus, 2-minus, 3-multiply, 4-divide

    public ArithExp(IExp e1, IExp e2, int op) {
//        List<Integer> ops = new ArrayList<Integer>();
//        ops.add(1); ops.add(2); ops.add(3); ops.add(4);
//        if (!ops.contains(op)) throw new ArithExpException("op not valid");
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() {
        switch (op) {
            case 1:
                return e1.toString() + "+" + e2.toString();

            case 2:
                return e1.toString() + "-" + e2.toString();

            case 3:
                return e1.toString() + "*" + e2.toString();

            case 4:
                return e1.toString() + "/" + e2.toString();
        }
       return null;
    }

    @Override
    public IExp deepCopy() {
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), op);
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

                switch (this.op) {
                    case 1:
                        return new IntValue(n1 + n2);

                    case 2:
                        return new IntValue(n1 - n2);


                    case 3:
                        return new IntValue(n1 * n2);


                    case 4:
                        if (n2 == 0) throw new DivisionByZeroException("division by zero");
                        return new IntValue(n1 / n2);

                }
            } else throw new TypeException("second operand is not an integer");

        } else throw new TypeException("first operand is not an integer");


        return v1;
    }
}
