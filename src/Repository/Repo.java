package Repository;

import Model.Exception.ADTException.MyListException;
import Model.Exception.MyException;
import Model.Exception.RepositoryException;
import Model.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Repo<T> implements IRepo<T>{
    private List<T> repo = new ArrayList<T>();
    private int curr = -1;
    private String logFilePath;

    public Repo() {};

    public Repo(T prg) {
        repo.add(prg);
        this.curr = 0;
    }

    public Repo(T prg, String logFilePath) {
        repo.add(prg);
        this.curr = 0;
        this.logFilePath = logFilePath;
        File file = new File(logFilePath);
        file.delete();
    }

    @Override
    public T getCrtPrg() {
        return repo.get(curr);
    }

    @Override
    public void add(T prg) throws MyException {
        for (T state : repo)
            if (state == prg)
                throw new RepositoryException("element already stored");
        repo.add(prg);
        if (curr == -1)
            curr = 0;
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write(state.toString());
        logFile.close();
    }

    @Override
    public List<T> getPrgList() {
        return repo;
    }

    @Override
    public void setPrgList(List<T> list) {
        repo = list;
    }

    @Override
    public boolean isEmpty() {
        return repo.isEmpty();
    }

//    @Override
//    public void addByIndex(int i, T elem) throws RepositoryException {
//        if (i < 0 || i >= repo.size())
//            throw new RepositoryException("index out of bounds");
//
//        repo.add(i, elem);
//    }
//
    @Override
    public T getByIndex(int i) throws MyListException {
        if (i < 0 || i >= repo.size())
            throw new MyListException("index out of bounds");

        return repo.get(i);
    }
//
//    @Override
//    public void removeByPos(int i) throws MyListException {
//        if (i < 0 || i >= repo.size())
//            throw new MyListException("index out of bounds");
//        repo.remove(i);
//    }
//
//    @Override
//    public void remove(T elem) throws MyListException {
//        if (!repo.contains(elem)) {
//            throw new MyListException("element not stored");
//        }
//        repo.remove(elem);
//    }

    @Override
    public IRepo<T> deepCopy() {
        return new Repo<T>(getCrtPrg(), logFilePath);
    }


}
