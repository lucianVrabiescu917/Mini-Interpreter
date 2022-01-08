package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.IType;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
