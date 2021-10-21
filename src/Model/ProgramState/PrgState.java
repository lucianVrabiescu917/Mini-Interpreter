package Model.ProgramState;

import Model.ADT.IMyDict;
import Model.ADT.IMyList;
import Model.ADT.IMyStack;
import Model.ADT.MyStack;
import Model.Statement.IStmt;
import Model.Value.IValue;

import java.util.Stack;

public class PrgState {
    IMyStack<IStmt> stk;
    IMyDict<String, IValue> symTable;
    IMyList<IValue> out;

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out, IStmt prg) {
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.stk.push(prg);
    }



    public void setStk(IMyStack<IStmt> stk) {
        this.stk = stk;
    }

    public void setSymTable(IMyDict<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IMyList<IValue> out) {
        this.out = out;
    }


    public IMyStack<IStmt> getStk() {
        return stk;
    }

    public IMyDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IMyList<IValue> getOut() {
        return out;
    }

    public String toString() {
        String stkS = "{";
        IMyStack<IStmt> stk2 = new MyStack<IStmt>();
        for (IStmt i : (MyStack<IStmt>)stk) {
            stkS += i.toString() + ";";
        }
        stkS += "{";

        return stkS;
    }



}
