package Model.Statement;

import Model.Exception.MyException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Value.IValue;

public class PrintStmt implements IStmt{
    IExp exp;

    public String toString() {
        return "print(" +exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getOut().add((IValue) exp.eval(state.getSymTable()));
        return state;
    }
}
