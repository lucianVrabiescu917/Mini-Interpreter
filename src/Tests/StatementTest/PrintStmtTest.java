package Tests.StatementTest;

import Model.ADT.*;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.IExp;
import Model.Expression.ValueExp;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Statement.PrintStmt;
import Model.Statement.VarDeclStmt;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PrintStmtTest {
    IExp vI1 = new ValueExp(new IntValue(5));
    IExp vI2 = new ValueExp(new IntValue(10));
    IExp exp = new ArithExp(vI1, vI2, 1);


    IMyStack<IStmt> stk = new MyStack<IStmt>();
    IMyDict<String, IValue> symTable = new MyDict<String, IValue>();
    IMyList<IValue> out = new MyList<IValue>();
    IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
    IStmt stm = new VarDeclStmt("a", new IntType());
    PrgState prg = new PrgState(stk, symTable, out, fT, stm);

    IStmt p = new PrintStmt(exp);

    @Test
    void toStringTest() {
        assertEquals("print(5+10)", p.toString());
    }

    @Test
    void execute() throws MyException, IOException {
        p.execute(prg);
        assertEquals( 15, ((IntValue)out.get(out.getLen()-1)).getVal() );
    }
}