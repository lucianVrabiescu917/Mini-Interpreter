package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.MyStack;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.IType;

import java.io.IOException;

public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt stmt) {this.stmt = stmt;}

    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.copy());
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        return new PrgState(new MyStack<IStmt>(), state.getSymTable().copy(), state.getOut(),
                                        state.getFileTable(), state.getHeap(),  stmt);
    }
}
