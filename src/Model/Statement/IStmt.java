package Model.Statement;

import Model.Exception.MyException;
import Model.ProgramState.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

}
