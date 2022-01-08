package Tests.StatementTest;

import Controller.Controller;
import Model.ADT.*;
import Model.Exception.MyException;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;
import Repository.IRepo;
import Repository.Repo;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileStmtsTest {

    @Test
    void exampleTest() throws IOException {
        /*string varf;
        varf="test.in";
        openRFile(varf);
        int varc;
        readFile(varf,varc);print(varc);
        readFile(varf,varc);print(varc)
        closeRFile(varf) */
        IStmt exp = new CompStmt(new VarDeclStmt("varf", new StringType()),
                                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\Latitude\\Documents\\GitHub\\Mini-Interpreter\\src\\Tests\\StatementTest\\testt.in"))),
                                    new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                            new CompStmt(new ReadFileStmt(new VarExp("varf"), new VarExp("varc")),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), new VarExp("varc")),
                                                        new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));

        IMyStack<IStmt> stk = new MyStack<IStmt>();
        IMyDict<String, IValue> sym = new MyDict<String, IValue>();
        IMyList<IValue> out = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
        PrgState prg = new PrgState(stk, sym, out, fT, exp);
        IRepo<PrgState> repo = new Repo<PrgState>(prg, "testRepo.in");
        Controller controller = new Controller(repo);

        try {
            System.out.println(controller.allStep());
        } catch (MyException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}