package Model.ProgramState;

import Model.ADT.IMyDict;
import Model.ADT.IMyList;
import Model.ADT.IMyStack;
import Model.ADT.MyStack;
import Model.Exception.ADTException.MyStackException;
import Model.Statement.IStmt;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.util.Stack;

public class PrgState {
    IMyStack<IStmt> stk;
    IMyDict<String, IValue> symTable;
    IMyList<IValue> out;
    IMyDict<String, BufferedReader> fileTable;
    IMyDict<Integer, IValue> heap;

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out,
                    IMyDict<String, BufferedReader> fileTable, IMyDict<Integer, IValue> heap ) {
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out,
                    IMyDict<String, BufferedReader> fileTable, IMyDict<Integer, IValue> heap, IStmt prg ) {
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.stk.push(prg);
    }

    public PrgState(IMyStack<IStmt> stk, IMyDict<String, IValue> symTable, IMyList<IValue> out, IMyDict<String, BufferedReader> fileTable, IStmt prg) {
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
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

    public IMyDict<String, BufferedReader> getFileTable() {return fileTable;}

    public IMyDict<Integer, IValue> getHeap() {return heap;}

    public String toString() {

        String res = "ExeStack:\n";
        res += stk.toString();

        res += "SymTable:\n";
        res += symTable.toString();

        res += "Out:\n";
        res += out.toString();

        res += "FileTable:\n";
        res += fileTable.toString();


        res += "Heap:\n";
        res += heap.toString();

        res += "\n";

        return res;
    }

    public PrgState deepCopy() {
        return new PrgState(stk, symTable, out, fileTable, heap);
    }


    public void setHeap(IMyDict<Integer, IValue> heap) {
        this.heap = heap;
    }
}
