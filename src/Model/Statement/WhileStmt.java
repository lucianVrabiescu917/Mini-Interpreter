package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

import java.io.IOException;

public class WhileStmt implements IStmt{
    IExp exp;

    public WhileStmt(IExp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "while(" + exp.toString() + ")";
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            return typeEnv;
        } else
            throw new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        if (!val.getType().equals(new BoolType()))
            throw new TypeException("condition expr is not a boolean");

        IMyStack<IStmt> stk = state.getStk();
        if (((BoolValue)val).getVal()) {
            IStmt whileStmt = stk.peek();
            stk.push(new WhileStmt(exp));
            stk.push(whileStmt);
        } else {
            stk.pop();
        }

        return null;
    }
}
