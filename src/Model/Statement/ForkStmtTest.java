package Model.Statement;

import Controller.Controller;
import Model.ADT.*;
import Model.Exception.ADTException.MyListException;
import Model.Expression.RHeapExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepo;
import Repository.Repo;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class ForkStmtTest {
    @Test
    void test() {
//        int v; Ref int a; v=10;new(a,22);
//        fork(wH(a,30);v=32;print(v);print(rH(a)));
//        print(v);print(rH(a))

        IStmt exp = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                    new CompStmt(
                                        new ForkStmt(
                                            new CompStmt(new WHeapStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new RHeapExp(new VarExp("a"))))))),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new RHeapExp(new VarExp("a")))))))));

        IMyStack<IStmt> stk = new MyStack<IStmt>();
        IMyDict<String, IValue> sym = new MyDict<String, IValue>();
        IMyList<IValue> out = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
        IMyDict<Integer, IValue> heap = new MyDictHeap<Integer, IValue>();
        PrgState prg = new PrgState(stk, sym, out, fT, heap, exp);
        IRepo<PrgState> repo = new Repo<PrgState>(prg, "testFork1.in");
        Controller controller = new Controller(repo);

        try {
            controller.allStep();
        } catch (InterruptedException | MyListException ex) {
            ex.printStackTrace();
        }
    }
}