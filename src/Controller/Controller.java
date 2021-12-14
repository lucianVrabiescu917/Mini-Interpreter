package Controller;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.ADT.MyDictHeap;
import Model.Exception.ADTException.MyDictException;
import Model.Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.IRepo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private IRepo<PrgState> repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    private Map<Integer, IValue> garbageCollector(List<Integer> symTableAddr, List<Integer> heapRefAddr, IMyDict<Integer,IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapRefAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues
                .stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue)v).getAddress())
                .collect(Collectors.toList());
    }

    private List<Integer> getRefFromHeap(IMyDict<Integer, IValue> heap) {
        return heap.getValues()
                .stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue)v).getAddress())
                .collect(Collectors.toList());
    }



    public PrgState oneStep(PrgState state) throws MyException, IOException {
        IMyStack<IStmt> stk = state.getStk();
        if (stk.isEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public String allStep() throws MyException, IOException {
        PrgState prg = repo.getCrtPrg();
        String res = prg.toString();
        repo.logPrgStateExec();
        while (!prg.getStk().isEmpty()) {
            oneStep(prg);
            res += prg.toString();
            repo.logPrgStateExec();
            ((MyDictHeap)prg.getHeap()).setContent(garbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getValues()),
                    getRefFromHeap(prg.getHeap()),
                    prg.getHeap()));
            res += prg.toString();
            repo.logPrgStateExec();
        }
        return res;
    }

    Controller deepCopy() {
        return new Controller(repo);
    }

}
