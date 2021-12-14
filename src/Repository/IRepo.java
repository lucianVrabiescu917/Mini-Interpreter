package Repository;

import Model.Exception.ADTException.MyListException;
import Model.Exception.MyException;
import Model.Exception.RepositoryException;
import Model.ProgramState.PrgState;

import java.io.IOException;

public interface IRepo<T> {
    T getCrtPrg();
    void add(T prg) throws MyException;
    void logPrgStateExec() throws MyException, IOException;
    void addByIndex(int index, T elem) throws RepositoryException;
    T getByIndex(int index) throws MyListException;
    void removeByPos(int index) throws MyListException;
    void remove(T elem) throws MyListException;
    IRepo<T> deepCopy();
}
