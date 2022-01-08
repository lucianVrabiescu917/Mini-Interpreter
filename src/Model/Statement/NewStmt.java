package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.io.IOException;
import java.util.Objects;

public class NewStmt implements IStmt{
    String var;
    IExp exp;

    public NewStmt(String var, IExp exp) {
        this.var = var;
        this.exp = exp;
    }

    public String toString() {
        return "new(" + var + ", " + exp.toString() + ")";
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.getValue(var);
        IType typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have "+
                    "different types ");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IMyDict<String, IValue> dict = state.getSymTable();

        if (!dict.isDefined(var))
            throw new VarException("variable is not declared");

        if (!(dict.getValue(var).getType() instanceof RefType))
            throw new TypeException("not a ref");

        IValue expVal = exp.eval(dict, state.getHeap());

        if (!((RefType) dict.getValue(var).getType()).getInner().equals(expVal.getType()))
            throw new TypeException("types of ref and value not matching");

        MyDictHeap<Integer, IValue> heap = (MyDictHeap<Integer, IValue>) state.getHeap();
        IValue refVal = new RefValue(heap.getNext(), expVal.getType());

        //the ref that point to the location of the variable that is overwritten will point to the new location
//        for (Integer key : heap.keySet()) {
//            if (heap.getValue(key).getType() instanceof RefType && ((RefValue)heap.getValue(key)).getAddress() == ((RefValue)dict.getValue(var)).getAddress()) {
//                ((RefValue) heap.getValue(key)).setAddress(heap.getNext());
//            }
//        }

        heap.add(expVal);

        dict.add(var, refVal);

        return null;


    }
}
