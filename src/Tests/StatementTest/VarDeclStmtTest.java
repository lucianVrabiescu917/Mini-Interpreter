package Tests.StatementTest;

import Model.ADT.*;
import Model.Exception.ExpressionException.ArithExpException;
import Model.Exception.MyException;
import Model.Exception.StatementException.VarDeclStmtException;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Statement.VarDeclStmt;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VarDeclStmtTest {

    IMyStack<IStmt> stk = new MyStack<IStmt>();
    IMyDict<String, IValue> symTable = new MyDict<String, IValue>();
    IMyList<IValue> out = new MyList<IValue>();
    IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
    IStmt stm = new VarDeclStmt("a", new IntType());
    PrgState prg = new PrgState(stk, symTable, out, fT, stm);


    @Test
    void execute() throws MyException, IOException {
        IStmt stm2 = new VarDeclStmt("b", new BoolType());
        stm2.execute(prg);
        assertFalse( ((BoolValue)(((MyDict)symTable).getValue("b"))).getVal() );

        assertThrows(VarDeclStmtException.class , () -> {
            IStmt stm3 = new VarDeclStmt("b", new BoolType());
            stm3.execute(prg);
        });


    }
}