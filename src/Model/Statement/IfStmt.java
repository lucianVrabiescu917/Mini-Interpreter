package Model.Statement;

import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class IfStmt implements IStmt{
    IExp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(IExp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+"))";}

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        IValue condition = exp.eval(state.getSymTable(), state.getHeap());

        if (!Objects.equals(condition.getType(), new BoolType()))
            throw new TypeException("conditional expr is not a boolean");

        BoolValue conditionB = (BoolValue) condition;
        if (conditionB.getVal()) {
            return thenS.execute(state);
        } else {
             return elseS.execute(state);
        }

    }
}
