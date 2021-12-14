package Model.Statement;

import Model.Exception.MyException;
import Model.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
    String toString();

}
