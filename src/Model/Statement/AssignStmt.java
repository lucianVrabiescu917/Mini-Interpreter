package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.Exception.MyException;
import Model.Exception.StatementException.AssignStmtException;
import Model.Exception.TypeException;
import Model.Exception.VarException;
import Model.Expression.IExp;
import Model.ProgramState.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt{
    String id;
    IExp exp;

    public AssignStmt(String id, IExp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public IMyDict<String, IType> typecheck(IMyDict<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.getValue(id);
        IType typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side" +
                    "have different types ");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyStack<IStmt> stk = state.getStk();
        IMyDict<String, IValue> symTbl = state.getSymTable();

        if (symTbl.isDefined(id)) {
            IValue val = exp.eval(symTbl, state.getHeap());
            IType typeId = symTbl.getValue(id).getType();
            if (val.getType().equals(typeId)) {
                symTbl.add(id, val);
            }
            else throw new TypeException("declared type of variable"+id+" and type of the assigned expression do not match");

        } else throw new VarException(("the used variable" +id + " was not declared before"));
        return null;
    }
}
