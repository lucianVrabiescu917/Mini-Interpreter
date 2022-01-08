package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ReadFileStmt implements IStmt{
    IExp exp;
    IExp var;

    public ReadFileStmt(IExp exp, IExp var) {
        this.exp = exp;
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IMyDict<String, IValue> dict = state.getSymTable();

        if (!dict.isDefined(var.toString()))
            throw new VarException("variable is not declared");

        if (!Objects.equals(var.eval(dict, state.getHeap()).getType(), new IntType()))
            throw new TypeException("not an int");

        IValue file = exp.eval(state.getSymTable(), state.getHeap());

        if (!Objects.equals(file.getType(), new StringType()))
            throw new TypeException("not a string");

        IMyDict<String, BufferedReader> ft = state.getFileTable();

        if (!ft.isDefined(file.toString()))
            throw new VarException("variable is not declared");

        BufferedReader br = ft.getValue(file.toString());

        String line = br.readLine();
        int no;
        if (line == null)
            no = 0;
        else
            no = Integer.parseInt(line);

        dict.add(exp.eval(dict, state.getHeap()).toString(), new IntValue(no));


        return null;
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType typevar = var.typecheck(typeEnv);
        IType typexp = exp.typecheck(typeEnv);
        if (!typevar.equals(new IntType()))
            throw new MyException("Var not int type");
        if (typexp.equals(new StringType()))
            throw new MyException("Exp not string type");
        return typeEnv;
    }
}
