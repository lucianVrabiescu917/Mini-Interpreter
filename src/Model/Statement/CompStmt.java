package Model.Statement;

import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }

    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
}
