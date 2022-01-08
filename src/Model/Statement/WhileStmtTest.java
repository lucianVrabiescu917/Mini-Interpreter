package Model.Statement;

import Controller.Controller;
import Model.ADT.*;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.RelExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
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

class WhileStmtTest {

    @Test
    void execute() {
        IStmt whileStmt = new CompStmt(new WhileStmt(new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">")),new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2))));
        IStmt exp = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                            new CompStmt(whileStmt, new PrintStmt(new VarExp("v")))));


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