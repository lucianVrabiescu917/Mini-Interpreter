package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class PrintStmt implements IStmt{
    IExp exp;

    public PrintStmt(IExp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" +exp.toString()+")";
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getOut().add((IValue) exp.eval(state.getSymTable(), state.getHeap()));

        return null;
    }
}
