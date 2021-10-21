package Model.Statement;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.Exception.MyException;
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

    public String toSting() {
        return id+"="+ exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IMyStack<IStmt> stk = state.getStk();
        IMyDict<String, IValue> symTbl = state.getSymTable();

        if (symTbl.isDefined(id)) {

            IValue val = exp.eval(symTbl);
            IType typeId = symTbl.getValue(id).getType();
            if (val.getType().equals(typeId))
                symTbl.addValue(id, val);
            else throw new MyException("declared type of variable"+id+" and type of the assigned expression do not match");

        } else throw new MyException(("the used variable" +id + " was not declared before"));
        return null;
    }
}
