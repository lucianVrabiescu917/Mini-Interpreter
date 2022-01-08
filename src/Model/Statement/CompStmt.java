package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.IType;

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
        return null;
    }

    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return snd.typecheck(first.typecheck(typeEnv));
    }
}
