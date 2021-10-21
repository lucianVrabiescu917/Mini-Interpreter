package Model.Statement;

import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

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
    public PrgState execute(PrgState state) throws MyException {

        IValue condition = exp.eval(state.getSymTable());

        if (!Objects.equals(condition.getType(), new BoolType()))
            throw new MyException("conditional expr is not a boolean");

        BoolValue conditionB = (BoolValue) condition;
        if (conditionB.getVal()) {
            return thenS.execute(state);
        } else {
             return elseS.execute(state);
        }

    }
}
