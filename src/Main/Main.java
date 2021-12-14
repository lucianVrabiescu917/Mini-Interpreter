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
//        IMyStack<IStmt> stk = new MyStack<IStmt>();
//        IMyDict<String, IValue> sym = new MyDict<String, IValue>();
//        IMyList<IValue> out = new MyList<IValue>();
//        IMyDict<String, BufferedReader> fT = new MyDictTable<String, BufferedReader>();
//
//        System.out.println("Enter which example to use");
//        System.out.println("1. int v; v=2;Print(v)");
//        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)");
//        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
//
//        Scanner in = new Scanner(System.in);
//
//        int e = in.nextInt();
//        PrgState prg;
//        StmtGenerator stmtGen = new StmtGenerator();
//        switch (e) {
//            case 1:
//                prg = new PrgState(stk, sym, out, fT, stmtGen.hardcoded1());
//                break;
//            case 2:
//                prg = new PrgState(stk, sym, out, fT, stmtGen.hardcoded2());
//                break;
//            case 3:
//                prg = new PrgState(stk, sym, out, fT, stmtGen.hardcoded3());
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + e);
//        }
//
//        IRepo<PrgState> repo = new Repo<PrgState>(prg);
//
//        Controller controller = new Controller(repo);
//        Console console = new Console(controller);
//        console.inputProgram();

        StmtGenerator stmtGen = new StmtGenerator();


        IMyStack<IStmt> stk1 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym1 = new MyDict<String, IValue>();
        IMyList<IValue> out1 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT1 = new MyDictTable<String, BufferedReader>();

        IStmt ex1 = stmtGen.hardcoded1();
        PrgState prg1 = new PrgState(stk1, sym1, out1, fT1, ex1);
        IRepo repo1 = new Repo(prg1,"log11.txt");
        Controller ctr1 = new Controller(repo1);

        IMyStack<IStmt> stk2 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym2 = new MyDict<String, IValue>();
        IMyList<IValue> out2 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT2 = new MyDictTable<String, BufferedReader>();

        IStmt ex2 = stmtGen.hardcoded2();
        PrgState prg2 = new PrgState(stk2, sym2, out2, fT2, ex2);
        IRepo repo2 = new Repo(prg2,"log22.txt");
        Controller ctr2 = new Controller(repo2);

        IMyStack<IStmt> stk3 = new MyStack<IStmt>();
        IMyDict<String, IValue> sym3 = new MyDict<String, IValue>();
        IMyList<IValue> out3 = new MyList<IValue>();
        IMyDict<String, BufferedReader> fT3 = new MyDictTable<String, BufferedReader>();

        IStmt ex3 = stmtGen.hardcoded3();
        PrgState prg3 = new PrgState(stk3, sym3, out3, fT3, ex3);
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

