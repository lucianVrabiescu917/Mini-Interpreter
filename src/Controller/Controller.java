package Controller;

import Model.ADT.IMyDict;
import Model.ADT.IMyStack;
import Model.ADT.MyDictHeap;
import Model.Exception.ADTException.MyDictException;
import Model.Exception.ADTException.MyListException;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo<PrgState> repo;
    private ExecutorService executor;

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

    private List<Integer> getAddrFromAllSymTables() {
        return repo.getPrgList()
                .stream()
                .map(prg -> (prg.getSymTable().getValues()))
                .map(e -> getAddrFromSymTable((Collection<IValue>) e))
                .collect(Collectors.toList())
                .stream().flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> getRefFromHeap(IMyDict<Integer, IValue> heap) {
        return heap.getValues()
                .stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue)v).getAddress())
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }


    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException  | IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                        catch(InterruptedException | ExecutionException e) {
                            return null;
                        }
                })
                .filter(p -> p!=null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException  | IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(prgList);
    }

    public String allStep() throws InterruptedException, MyListException {
        executor = Executors.newFixedThreadPool(2);

        IMyDict<Integer, IValue> heap = null;
        if (!repo.isEmpty())
            heap =  repo.getByIndex(0).getHeap();

        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
            ((MyDictHeap)heap).setContent(garbageCollector(
                    getAddrFromAllSymTables(),
                    getRefFromHeap(heap),
                    heap));
        }
        executor.shutdownNow();

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException  | IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(prgList);
        return "";
    }

    Controller deepCopy() {
        return new Controller(repo);
    }

}
