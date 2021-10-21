package Model.Statement;

import Model.ADT.IMyDict;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

import java.util.Objects;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyDict<String, IValue> dict = state.getSymTable();

        if (dict.isDefined(name))
            throw new MyException("variable is already declared");

        if (Objects.equals(type, new BoolType()))
            dict.addValue(name, new BoolValue(false));

        else if (Objects.equals(type, new IntType()))
            dict.addValue(name, new IntValue(0));

        return state;
        
    }
}
