package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.IType;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
    String toString();
    IMyDict<String,IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException;
}
