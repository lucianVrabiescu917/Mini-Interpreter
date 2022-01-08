package Main;

import Controller.Controller;
import Model.ADT.*;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

import Repository.IRepo;
import Repository.Repo;
import View.Console;
import View.ExitCommand;
import View.RunExampleCommand;
import View.TextMenu;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MyException, IOException {

        StmtGenerator stmtGen = new StmtGenerator();

        IMyStack<IStmt> stk1 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym1 = new MyDict<String, IValue>();
        IMyList<IValue> out1 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT1 = new MyDictTable<String, BufferedReader>();
        IMyDict<Integer, IValue> heap1 = new MyDictHeap<>();

        IStmt ex1 = stmtGen.hardcoded1();
        PrgState prg1 = new PrgState(stk1, sym1, out1, fT1, heap1, ex1);
        IRepo repo1 = new Repo(prg1,"log11.txt");
        Controller ctr1 = new Controller(repo1);

        IMyStack<IStmt> stk2 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym2 = new MyDict<String, IValue>();
        IMyList<IValue> out2 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT2 = new MyDictTable<String, BufferedReader>();
        IMyDict<Integer, IValue> heap2 = new MyDictHeap<>();

        IStmt ex2 = stmtGen.hardcoded2();
        PrgState prg2 = new PrgState(stk2, sym2, out2, fT2, heap2, ex2);
        IRepo repo2 = new Repo(prg2,"log22.txt");
        Controller ctr2 = new Controller(repo2);

        IMyStack<IStmt> stk3 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym3 = new MyDict<String, IValue>();
        IMyList<IValue> out3 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT3 = new MyDictTable<String, BufferedReader>();
        IMyDict<Integer, IValue> heap3 = new MyDictHeap<>();

        IStmt ex3 = stmtGen.hardcoded3();
        PrgState prg3 = new PrgState(stk3, sym3, out3, fT3, heap3, ex3);
        IRepo repo3 = new Repo(prg3,"log33.txt");
        Controller ctr3 = new Controller(repo3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExampleCommand("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExampleCommand("3",ex3.toString(),ctr3));
        menu.show();
    }
}

