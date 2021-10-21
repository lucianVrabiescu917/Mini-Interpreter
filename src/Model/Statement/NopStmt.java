package Model.Statement;

import Model.Exception.MyException;
import Model.ProgramState.PrgState;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
}
