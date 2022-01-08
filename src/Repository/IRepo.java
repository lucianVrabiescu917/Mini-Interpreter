package Repository;

import Model.Exception.ADTException.MyListException;
import Model.Exception.MyException;
import Model.Exception.RepositoryException;
import Model.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo<T> {
    T getCrtPrg();
    void add(T prg) throws MyException;
    void logPrgStateExec(PrgState state) throws MyException, IOException;
    List<T> getPrgList();
    void setPrgList(List<T> list);
    boolean isEmpty();
    public T getByIndex(int i) throws MyListException;
    IRepo<T> deepCopy();
}
