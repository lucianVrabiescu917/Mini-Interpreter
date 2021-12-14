package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.MyDictHeap;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.io.IOException;

public class WHeapStmt implements IStmt{
    String varName;
    IExp exp;

    public WHeapStmt(String varName, IExp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    public String toString() {
        return "wH(" + varName + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        Model.ADT.IMyDict<String, IValue> tbl = state.getSymTable();
        IMyDict<Integer, IValue> heap = state.getHeap();

        if (!tbl.isDefined(varName))
            throw new VarException("variable not stored");

        if (!(tbl.getValue(varName).getType() instanceof RefType))
            throw new TypeException("not a ref");

        int address = ((RefValue)tbl.getValue(varName)).getAddress();

        if (!heap.isDefined(address))
            throw new VarException("address not stored");

        IValue valExp = exp.eval(tbl, heap);

        if (!((RefType)(tbl.getValue(varName)).getType()).getInner().equals(valExp.getType()))
            throw new TypeException("ref type and value type are not the same");

        ((MyDictHeap<Integer, IValue>)heap).change(address, valExp);

        return state;
    }
}
