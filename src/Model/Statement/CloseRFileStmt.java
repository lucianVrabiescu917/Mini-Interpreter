package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class CloseRFileStmt implements IStmt{
    IExp exp;

    public CloseRFileStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        IValue file = exp.eval(state.getSymTable(), state.getHeap());

        if (!Objects.equals(file.getType(), new StringType()))
            throw new TypeException("not a string");

        IMyDict<String, BufferedReader> ft = state.getFileTable();

        if (!ft.isDefined(file.toString()))
            throw new VarException("variable is not declared");

        BufferedReader br = ft.getValue(file.toString());
        br.close();

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
