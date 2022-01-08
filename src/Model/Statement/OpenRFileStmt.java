package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.FileException;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class OpenRFileStmt implements IStmt {
    IExp exp;

    public OpenRFileStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {

        IValue file = exp.eval(state.getSymTable(), state.getHeap());

        if (!Objects.equals(file.getType(), new StringType()))
            throw new TypeException("not a string");

        if (state.getFileTable().isDefined(file.toString()))
            throw new VarException("variable is already declared");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.toString()));
            state.getFileTable().add(file.toString(), br);
        } catch (IOException  ex) {
            throw new FileException(ex.getMessage());
        }

        return null;
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType type = exp.typecheck(typeEnv);
        if (type.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("OpenRFile stmt: exp not a string");
    }
}
