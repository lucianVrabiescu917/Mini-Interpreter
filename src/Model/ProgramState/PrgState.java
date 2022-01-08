package Model.ProgramState;

import Model.ADT.IMyDict;
import Model.ADT.IMyList;
import Model.ADT.IMyStack;
import Model.ADT.MyStack;
import Model.Exception.ADTException.MyStackException;
import Model.Exception.MyException;
import Model.Statement.IStmt;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class PrgState {
    static int counter = 0;
    int id;
    IMyStack<IStmt> stk;
    IMyDict<String, IValue> symTable;
    IMyList<IValue> out;
    IMyDict<String, BufferedReader> fileTable;
    IMyDict<Integer, IValue> heap;

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out,
                    IMyDict<String, BufferedReader> fileTable, IMyDict<Integer, IValue> heap ) {
        this.id = counter;
        PrgState.updateCounter();
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out,
                    IMyDict<String, BufferedReader> fileTable, IMyDict<Integer, IValue> heap, IStmt prg ) {
        this(stk, symTable, out, fileTable, heap);
        this.stk.push(prg);
    }

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out, IMyDict<String, BufferedReader> fileTable, IStmt prg) {
        this.id = counter;
        PrgState.updateCounter();
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.stk.push(prg);
    }

    synchronized static void  updateCounter() {
        counter++;
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

    public IMyDict<String, BufferedReader> getFileTable() {return fileTable;}

    public IMyDict<Integer, IValue> getHeap() {return heap;}

    public boolean isNotCompleted() {return !stk.isEmpty();}

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Id: ");
        res.append(id);
        res.append("\nExeStack:\n");
        res.append(stk.toString());

        res.append("SymTable:\n");
        res.append(symTable.toString());

        res.append("Out:\n");
        res.append(out.toString());

        res.append("FileTable:\n");
        res.append(fileTable.toString());


        res.append("Heap:\n");
        res.append(heap.toString());

        res.append("\n");

        return res.toString();
    }

    public PrgState oneStep() throws MyException, IOException {
        if(stk.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(this);
    }

    public PrgState deepCopy() {
        return new PrgState(stk, symTable, out, fileTable, heap);
    }

    public void setHeap(IMyDict<Integer, IValue> heap) {
        this.heap = heap;
    }
}
