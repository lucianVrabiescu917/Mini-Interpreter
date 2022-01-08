package Main;

import Model.ADT.IMyDict;
import Model.ADT.MyDict;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class StmtGenerator {

    public IStmt hardcoded1() {
        // int v; v=2;Print(v)
        IStmt ex = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new BoolValue(false))), new PrintStmt(new
                        VarExp("v"))));
        IMyDict<String, IType> typenv = new MyDict<>();
        try {
            ex.typecheck(typenv);
        } catch (MyException err) {
            System.out.println(err);
        }
        return ex;
    }

    public IStmt hardcoded2() throws MyException {
//      int a;int b; a=2+3*5;b=a+1;Print(b) is represented as:
        IStmt ex = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        IMyDict<String, IType> typenv = new MyDict<>();
        try {
            ex.typecheck(typenv);
        } catch (MyException err) {
            System.out.println(err);
        }
        return ex;
    }

    public IStmt hardcoded3() {
//      bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v) is represented as
        IStmt ex = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        IMyDict<String, IType> typenv = new MyDict<>();
        try {
            ex.typecheck(typenv);
        } catch (MyException err) {
            System.out.println(err);
        }
        return ex;
    }

}
