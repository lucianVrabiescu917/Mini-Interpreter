package Tests.StatementTest;

import Controller.Controller;
import Model.ADT.*;
import Model.Exception.MyException;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.IRepo;
import Repository.Repo;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NewStmtTest {

    @Test
    void execute() {
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStmt exp = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new NewStmt("a", new VarExp("v")),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        try {
            IMyDict<String, IType> typeEnv = new MyDict<String, IType>();
            exp.typecheck(typeEnv);
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        IMyStack<IStmt> stk = new MyStack<IStmt>();
        IMyDict<String, IValue> sym = new MyDict<String, IValue>();
        IMyList<IValue> out = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
        IMyDict<Integer, IValue> heap = new MyDictHeap<Integer, IValue>();
        PrgState prg = new PrgState(stk, sym, out, fT, heap, exp);
        IRepo<PrgState> repo = new Repo<PrgState>(prg, "testRepo2.in");
        Controller controller = new Controller(repo);

        try {
            System.out.println(controller.allStep());
        } catch (MyException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}